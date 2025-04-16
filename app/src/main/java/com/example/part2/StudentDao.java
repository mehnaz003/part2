// Data Access Object interface for Student SQL queries

package com.example.part2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

@Dao
public interface StudentDao {

    @Insert
    void insertStudent(Student student);

    @Transaction
    @Query("SELECT * FROM students WHERE studentId = :studentId")
    StudentWithCourses getStudentWithCourses(int studentId);

    @Update
    void updateStudent(Student student);

    @Delete
    void deleteStudent(Student student);

    @Insert
    void enrollStudentInCourse(CourseStudent courseStudent);
}
