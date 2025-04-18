package com.example.part2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateCourseActivity extends AppCompatActivity {

    public static final String EXTRA_COURSE_CODE = "EXTRA_COURSE_CODE";
    public static final String EXTRA_COURSE_NAME = "EXTRA_COURSE_NAME";
    public static final String EXTRA_LECTURER_NAME = "EXTRA_LECTURER_NAME";

    private EditText editCourseCode;
    private EditText editCourseName;
    private EditText editLecturerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);

        editCourseCode = findViewById(R.id.edit_course_code);
        editCourseName = findViewById(R.id.edit_course_name);
        editLecturerName = findViewById(R.id.edit_lecturer_name);

        Button buttonCreate = findViewById(R.id.button_create_course);
        buttonCreate.setOnClickListener(view -> {
            Intent replyIntent = new Intent();

            String code = editCourseCode.getText().toString().trim();
            String name = editCourseName.getText().toString().trim();
            String lecturer = editLecturerName.getText().toString().trim();

            if (TextUtils.isEmpty(code) ||
                    TextUtils.isEmpty(name) ||
                    TextUtils.isEmpty(lecturer)) {

                Toast.makeText(this, "Please fill in all fields",
                        Toast.LENGTH_SHORT).show();
                setResult(Activity.RESULT_CANCELED, replyIntent);
                
                Log.e("CreateCourseActivity",
                        "Course creation failed: One or more fields are empty");
            }
            else {
                replyIntent.putExtra(EXTRA_COURSE_CODE, code);
                replyIntent.putExtra(EXTRA_COURSE_NAME, name);
                replyIntent.putExtra(EXTRA_LECTURER_NAME, lecturer);
                setResult(Activity.RESULT_OK, replyIntent);

                Log.d("CreateCourseActivity",
                        "Course created successfully: " +
                                code + ", " + name + ", " + lecturer);
                finish();
            }
        });
    }
}
