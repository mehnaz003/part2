package com.example.part2;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class StudentDetailsActivity extends AppCompatActivity {

    private TextView nameView, emailView, userNameView;
    private RecyclerView coursesRecyclerView;
    private StudentRepository studentRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        nameView = findViewById(R.id.studentName);
        emailView = findViewById(R.id.studentEmail);
        userNameView = findViewById(R.id.studentUserName);
        coursesRecyclerView = findViewById(R.id.studentCoursesRecyclerView);

        coursesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentRepo = new StudentRepository(getApplication());

        int studentId = getIntent().getIntExtra("studentId", -1);

        SystemDB.databaseWriteExecutor.execute(() -> {
            StudentWithCourses swc = studentRepo.getStudentWithCourses(studentId);
            runOnUiThread(() -> {
                if (swc != null && swc.student != null) {
                    nameView.setText(swc.student.getName());
                    emailView.setText(swc.student.getEmail());
                    userNameView.setText(swc.student.getUserName());

                    CourseAdapter adapter = new CourseAdapter(swc.courses);
                    coursesRecyclerView.setAdapter(adapter);
                }
            });
        });
    }
}
