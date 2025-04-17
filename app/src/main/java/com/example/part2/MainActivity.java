package com.example.part2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private CourseViewModel courseViewModel;
    private ActivityResultLauncher<Intent> createCourseLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.courseRecyclerView);
        final CourseListAdapter adapter = new CourseListAdapter(new CourseListAdapter.CourseDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getAllCourses().observe(this, adapter::submitList);

        createCourseLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        String courseCode = data.getStringExtra(
                                CreateCourseActivity.EXTRA_COURSE_CODE);
                        String courseName = data.getStringExtra(
                                CreateCourseActivity.EXTRA_COURSE_NAME);
                        String lecturerName = data.getStringExtra(
                                CreateCourseActivity.EXTRA_LECTURER_NAME);

                        Course course = new Course();
                        course.setCourseCode(courseCode);
                        course.setCourseName(courseName);
                        course.setLecturerName(lecturerName);

                        if (courseCode == null || courseCode.trim().isEmpty() ||
                                courseName == null || courseName.trim().isEmpty() ||
                                lecturerName == null || lecturerName.trim().isEmpty()) {

                            Toast.makeText(getApplicationContext(),
                                    "All fields must be filled in.",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }

                        courseViewModel.insertCourse(course, new CourseViewModel.InsertCallback() {
                            @Override
                            public void onSuccess() {
                                runOnUiThread(() -> {
                                    Toast.makeText(getApplicationContext(),
                                            "Course saved successfully",
                                            Toast.LENGTH_SHORT).show();
                                    Log.d("MainActivity", "Inserted course: "
                                            + course.getCourseCode() + ", " +
                                            course.getCourseName() + ", " +
                                            course.getLecturerName());
                                });
                            }

                            @Override
                            public void onError(Exception e) {
                                runOnUiThread(() -> {
                                    Toast.makeText(getApplicationContext(),
                                            "Error: Course code already exists.",
                                            Toast.LENGTH_SHORT).show();
                                    Log.e("MainActivity", "Course insertion failed: "
                                            + e.getMessage(), e);
                                });
                            }
                        });
                    }
                });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CreateCourseActivity.class);
            createCourseLauncher.launch(intent);
        });
    }
}