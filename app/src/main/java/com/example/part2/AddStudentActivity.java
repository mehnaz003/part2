package com.example.part2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddStudentActivity extends AppCompatActivity {

    private EditText nameInput, emailInput, userNameInput;
    private StudentRepository studentRepo;
    private int courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        nameInput = findViewById(R.id.inputName);
        emailInput = findViewById(R.id.inputEmail);
        userNameInput = findViewById(R.id.inputUserName);
        Button addButton = findViewById(R.id.addStudentButton);

        studentRepo = new StudentRepository(getApplication());

        courseId = getIntent().getIntExtra("courseId", -1);

        addButton.setOnClickListener(v -> addStudentToCourse());
    }

    private void addStudentToCourse() {
        String name = nameInput.getText().toString();
        String email = emailInput.getText().toString();
        String userName = userNameInput.getText().toString();

        SystemDB.databaseWriteExecutor.execute(() -> {
            SystemDB db = SystemDB.getDatabase(getApplicationContext());

            int studentId = db.studentDao().getStudentIdByUserName(userName);

            if (studentId == 0) {
                Student newStudent = new Student();
                newStudent.setName(name);
                newStudent.setEmail(email);
                newStudent.setUserName(userName);
                db.studentDao().insertStudent(newStudent);

                try { Thread.sleep(200); } catch (InterruptedException ignored) {}

                studentId = db.studentDao().getStudentIdByUserName(userName);
            }

            int exists = db.studentDao().isStudentEnrolled(courseId, userName);
            if (exists > 0) {
                runOnUiThread(() -> {
                    Toast.makeText(this,
                            "Student already enrolled", Toast.LENGTH_SHORT).show();
                    finish();
                });
                return;
            }

            CourseStudent cs = new CourseStudent();
            cs.setCourseId(courseId);
            cs.setStudentId(studentId);
            db.studentDao().addStudentToCourse(cs);

            runOnUiThread(() -> {
                Toast.makeText(this, "Student added", Toast.LENGTH_SHORT).show();
                finish();
            });
        });
    }
}