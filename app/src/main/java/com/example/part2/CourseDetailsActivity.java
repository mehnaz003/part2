package com.example.part2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CourseDetailsActivity extends AppCompatActivity {

    private CourseViewModel courseViewModel;
    private StudentViewModel studentViewModel;
    private TextView courseCodeText, courseNameText, lecturerNameText;
    private RecyclerView studentsRecyclerView;
    private StudentListAdapter adapter;
    private int courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        courseId = getIntent().getIntExtra("COURSE_ID", -1);
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

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        loadCourseDetails(courseId);

        adapter.setOnStudentClickListener(student -> {
            Intent intent = new Intent(this, StudentDetailsActivity.class);
            intent.putExtra("studentId", student.getStudentId());
            startActivity(intent);
        });

        adapter.setOnStudentLongClickListener(student -> {
            showEditRemoveDialog(student);
        });

        FloatingActionButton fab = findViewById(R.id.fab_add_student);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddStudentActivity.class);
            intent.putExtra("courseId", courseId);
            startActivity(intent);
        });
    }

    private void loadCourseDetails(int courseId) {
        SystemDB.databaseWriteExecutor.execute(() -> {
            CourseWithStudents courseWithStudents = courseViewModel.getCourseWithStudents(courseId);
            if (courseWithStudents != null) {
                runOnUiThread(() -> {
                    Course course = courseWithStudents.course;
                    courseCodeText.setText(course.getCourseCode());
                    courseNameText.setText(course.getCourseName());
                    lecturerNameText.setText(course.getLecturerName());
                    adapter.setStudents(new ArrayList<>(courseWithStudents.students));
                    Log.d("CourseDetails", "Students count: " + courseWithStudents.students.size());
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadCourseDetails(courseId);
    }

    private void showEditRemoveDialog(Student student) {
        new AlertDialog.Builder(this)
                .setTitle("Select Action")
                .setItems(new String[]{"Edit", "Remove"}, (dialog, which) -> {

                    if (which == 0) {
                        Intent intent = new Intent(CourseDetailsActivity.this,
                                EditStudentActivity.class);
                        intent.putExtra("studentId", student.getStudentId());
                        startActivity(intent);
                    }

                    else if (which == 1) {
                        removeStudentFromCourse(student);
                    }
                })
                .show();
    }

    private void removeStudentFromCourse(Student student) {
        SystemDB.databaseWriteExecutor.execute(() -> {
            studentViewModel.removeStudentFromCourse(courseId, student.getStudentId());
            runOnUiThread(() -> {
                Toast.makeText(this, "Student removed from course",
                        Toast.LENGTH_SHORT).show();
                loadCourseDetails(courseId);
            });
        });
    }

}
