package com.example.part2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private CourseViewModel courseViewModel;

    public static final int CREATE_COURSE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.courseRecyclerView);
        final CourseListAdapter adapter = new CourseListAdapter(new CourseListAdapter.CourseDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getAllCourses().observe(this, courses -> {
            adapter.submitList(courses);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CreateCourseActivity.class);
            startActivityForResult(intent, CREATE_COURSE_REQUEST_CODE);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CREATE_COURSE_REQUEST_CODE && resultCode == RESULT_OK) {
            String courseCode = data.getStringExtra(CreateCourseActivity.EXTRA_COURSE_CODE);
            String courseName = data.getStringExtra(CreateCourseActivity.EXTRA_COURSE_NAME);
            String lecturerName = data.getStringExtra(CreateCourseActivity.EXTRA_LECTURER_NAME);

            Course course = new Course();
            course.setCourseCode(courseCode);
            course.setCourseName(courseName);
            course.setLecturerName(lecturerName);
            courseViewModel.insertCourse(course);

        } else {
            Toast.makeText(getApplicationContext(),
                    "Course not saved: all fields must be completed",
                    Toast.LENGTH_SHORT).show();
        }
    }
}