package com.example.part2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CourseViewModel extends AndroidViewModel {

    private final CourseRepository courseRepository;
    private final LiveData<List<Course>> allCourses;

    public CourseViewModel(@NonNull Application application) {
        super(application);
        courseRepository = new CourseRepository(application);
        allCourses = courseRepository.getAllCourses();
    }

    public LiveData<List<Course>> getAllCourses() {
        return allCourses;
    }

    public void insertCourse(Course course, InsertCallback callback) {
        courseRepository.insertCourse(course, callback);
    }

    public interface InsertCallback {
        void onSuccess();
        void onError(Exception e);
    }

    public void deleteCourse(Course course) {
        courseRepository.deleteCourse(course);
        int courseId = course.getCourseId();
        courseRepository.deleteEnrollmentsByCourseId(courseId);
    }

    public void removeStudentFromCourse(int courseId, int studentId) {
        courseRepository.removeStudentFromCourse(courseId, studentId);
    }

    public CourseWithStudents getCourseWithStudents(int courseId) {
        return courseRepository.getCourseWithStudents(courseId);
    }

}
