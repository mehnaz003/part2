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

            if (TextUtils.isEmpty(editCourseCode.getText()) ||
                    TextUtils.isEmpty(editCourseName.getText()) ||
                    TextUtils.isEmpty(editLecturerName.getText())) {

                setResult(Activity.RESULT_CANCELED, replyIntent);
                Log.e("CreateCourseActivity",
                        "Course creation failed: One or more fields are empty");
            }
            else {

                String code = editCourseCode.getText().toString();
                String name = editCourseName.getText().toString();
                String lecturer = editLecturerName.getText().toString();

                replyIntent.putExtra(EXTRA_COURSE_CODE, code);
                replyIntent.putExtra(EXTRA_COURSE_NAME, name);
                replyIntent.putExtra(EXTRA_LECTURER_NAME, lecturer);
                setResult(Activity.RESULT_OK, replyIntent);

                Toast.makeText(this, "Course created successfully!",
                        Toast.LENGTH_SHORT).show();

                Log.d("CreateCourseActivity",
                        "Course created successfully: " +
                        code + ", " + name + ", " + lecturer);
            }

            finish();
        });
    }

}
