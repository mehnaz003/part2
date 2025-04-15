// Data Access Object interface for Student SQL queries

package com.example.part2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public interface StudentDao {

    @Insert
    void insertStudent(Student student);

    @Transaction
    @Query("SELECT * FROM students WHERE studentId = :studentId")
    StudentWithCourses getStudentWithCourses(int studentId);
}
