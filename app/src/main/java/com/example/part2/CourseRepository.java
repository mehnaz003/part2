package com.example.part2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CourseRepository {

    private final CourseDao courseDao;

    public CourseRepository(Application application) {
        SystemDB db = SystemDB_Impl.getDatabase(application);
        courseDao = db.courseDao();
    }

    public LiveData<List<Course>> getAllCourses() {
        return courseDao.getAllCourses();
    }

    public void insertCourse(Course course, CourseViewModel.InsertCallback callback) {
        SystemDB.databaseWriteExecutor.execute(() -> {
            try {
                courseDao.insertCourse(course);
                callback.onSuccess();
            } catch (Exception e) {
                callback.onError(e);
            }
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

    public void deleteEnrollmentsByCourseId(int courseId) {
        SystemDB.databaseWriteExecutor.execute(() -> {
            courseDao.deleteEnrollmentsByCourseId(courseId);
        });
    }
}
