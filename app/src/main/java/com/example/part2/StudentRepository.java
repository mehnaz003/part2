package com.example.part2;

import android.app.Application;
public class StudentRepository {

    private final StudentDao studentDao;

    public StudentRepository(Application application) {
        SystemDB db = SystemDB.getDatabase(application);
        studentDao = db.studentDao();
    }

    public void insertStudent(Student student) {
        SystemDB.databaseWriteExecutor.execute(() -> {
            studentDao.insertStudent(student);
        });
    }

    public StudentWithCourses getStudentWithCourses(int studentId) {
        return studentDao.getStudentWithCourses(studentId);
    }

    public void updateStudent(Student student) {
        SystemDB.databaseWriteExecutor.execute(() -> {
            studentDao.updateStudent(student);
        });
    }

    public void deleteStudent(Student student) {
        SystemDB.databaseWriteExecutor.execute(() -> {
            studentDao.deleteStudent(student);
        });
    }

    public void enrollStudentInCourse(CourseStudent courseStudent) {
        SystemDB.databaseWriteExecutor.execute(() -> {
            studentDao.enrollStudentInCourse(courseStudent);
        });
    }
}
