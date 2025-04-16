package com.example.part2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class CourseViewModel extends AndroidViewModel {

    private final CourseRepository courseRepository;
    private final MutableLiveData<List<Course>> allCourses = new MutableLiveData<>();

    public CourseViewModel(@NonNull Application application) {
        super(application);
        courseRepository = new CourseRepository(application);
        loadCourses();
    }

    public LiveData<List<Course>> getAllCourses() {
        return allCourses;
    }

    private void loadCourses() {
        SystemDB.databaseWriteExecutor.execute(() -> {
            List<Course> courseList = courseRepository.getAllCourses();
            allCourses.postValue(courseList);
        });
    }

    public void insertCourse(Course course) {
        courseRepository.insertCourse(course);
        loadCourses();
    }

    public void deleteCourse(Course course) {
        courseRepository.deleteCourse(course);
        loadCourses();
    }

    public void removeStudentFromCourse(int courseId, int studentId) {
        courseRepository.removeStudentFromCourse(courseId, studentId);
    }

    public CourseWithStudents getCourseWithStudents(int courseId) {
        return courseRepository.getCourseWithStudents(courseId);
    }
}
