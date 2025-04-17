package com.example.part2;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CourseDetailsActivity extends AppCompatActivity {

    private CourseViewModel courseViewModel;
    private TextView courseCodeText, courseNameText, lecturerNameText;
    private RecyclerView studentsRecyclerView;
    private StudentListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        int courseId = getIntent().getIntExtra("COURSE_ID", -1);
        if (courseId == -1) {
            Toast.makeText(this, "Invalid course ID", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        courseCodeText = findViewById(R.id.course_code_text);
        courseNameText = findViewById(R.id.course_name_text);
        lecturerNameText = findViewById(R.id.lecturer_name_text);
        studentsRecyclerView = findViewById(R.id.students_recycler_view);

        adapter = new StudentListAdapter();
        studentsRecyclerView.setAdapter(adapter);
        studentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        SystemDB.databaseWriteExecutor.execute(() -> {
            CourseWithStudents courseWithStudents = courseViewModel.getCourseWithStudents(courseId);
            if (courseWithStudents != null) {
                runOnUiThread(() -> {
                    Course course = courseWithStudents.course;
                    courseCodeText.setText(course.getCourseCode());
                    courseNameText.setText(course.getCourseName());
                    lecturerNameText.setText(course.getLecturerName());
                    adapter.setStudents(courseWithStudents.students);
                });
            }
        });
    }
}
