package com.example.part2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditStudentActivity extends AppCompatActivity {

    private TextView nameView, emailView, userNameView;
    private Button saveButton;
    private StudentRepository studentRepository;
    private int studentId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        nameView = findViewById(R.id.editStudentName);
        emailView = findViewById(R.id.editStudentEmail);
        userNameView = findViewById(R.id.editStudentUserName);
        saveButton = findViewById(R.id.saveButton);

        studentRepository = new StudentRepository(getApplication());

        studentId = getIntent().getIntExtra("studentId", -1);

        loadStudentDetails(studentId);

        saveButton.setOnClickListener(v -> {
            updateStudentDetails();
        });
    }

    private void loadStudentDetails(int studentId) {
        SystemDB.databaseWriteExecutor.execute(() -> {
            Student student = studentRepository.getStudentById(studentId);
            runOnUiThread(() -> {
                if (student != null) {
                    nameView.setText(student.getName());
                    emailView.setText(student.getEmail());
                    userNameView.setText(student.getUserName());
                }
            });
        });
    }

    private void updateStudentDetails() {
        String updatedName = nameView.getText().toString();
        String updatedEmail = emailView.getText().toString();
        String updatedUserName = userNameView.getText().toString();

        Student updatedStudent = new Student();
        updatedStudent.setStudentId(studentId);
        updatedStudent.setName(updatedName);
        updatedStudent.setEmail(updatedEmail);
        updatedStudent.setUserName(updatedUserName);

        studentRepository.updateStudent(updatedStudent);

        finish();

    }
}
