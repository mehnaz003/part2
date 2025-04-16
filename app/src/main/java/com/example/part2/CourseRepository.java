package com.example.part2;

import android.app.Application;

import java.util.List;

public class CourseRepository {

    private final CourseDao courseDao;

    public CourseRepository(Application application) {
        SystemDB db = SystemDB_Impl.getDatabase(application);
        courseDao = db.courseDao();
    }

    public List<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }

    public void insertCourse(Course course) {
        SystemDB.databaseWriteExecutor.execute(() -> {
            courseDao.insertCourse(course);
        });
    }

    public CourseWithStudents getCourseWithStudents(int courseId) {
        return courseDao.getCourseWithStudents(courseId);
    }

    public void removeStudentFromCourse(int courseId, int studentId) {
        SystemDB.databaseWriteExecutor.execute(() -> {
            courseDao.removeStudentFromCourse(courseId, studentId);
        });
    }

    public void deleteCourse(Course course) {
        SystemDB.databaseWriteExecutor.execute(() -> {
            courseDao.deleteCourse(course);
        });
    }
}
