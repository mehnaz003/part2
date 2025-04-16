// Data Access Object interface for Course SQL queries

package com.example.part2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface CourseDao {

    @Query("SELECT * FROM courses")
    List<Course> getAllCourses();

    @Insert
    void insertCourse(Course course);

    @Transaction
    @Query("SELECT * FROM courses WHERE courseId = :courseId")
    CourseWithStudents getCourseWithStudents(int courseId);

    @Query("DELETE FROM course_student WHERE courseId = :courseId AND studentId = :studentId")
    void removeStudentFromCourse(int courseId, int studentId);

    @Delete
    void deleteCourse(Course course);
}
