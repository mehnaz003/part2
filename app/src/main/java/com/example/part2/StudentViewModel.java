package com.example.part2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class StudentViewModel extends AndroidViewModel {

    private final StudentRepository studentRepository;

    public StudentViewModel(@NonNull Application application) {
        super(application);
        studentRepository = new StudentRepository(application);
    }

    public void insertStudent(Student student) {
        studentRepository.insertStudent(student);
    }

    public StudentWithCourses getStudentWithCourses(int studentId) {
        return studentRepository.getStudentWithCourses(studentId);
    }

    public void updateStudent(Student student) {
        studentRepository.updateStudent(student);
    }

    public void deleteStudent(Student student) {
        studentRepository.deleteStudent(student);
    }

    public void addStudentToCourse(CourseStudent courseStudent) {
        studentRepository.addStudentToCourse(courseStudent);
    }

    public void removeStudentFromCourse(int courseId, int studentId) {
        studentRepository.removeStudentFromCourse(courseId, studentId);
    }
}
