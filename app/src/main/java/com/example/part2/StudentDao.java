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
    void addStudentToCourse(CourseStudent courseStudent);

    @Query("SELECT COUNT(*) FROM course_student " +
            "JOIN students ON course_student.studentId = students.studentId " +
            "WHERE course_student.courseId = :courseId AND students.user_name = :userName")
    int isStudentEnrolled(int courseId, String userName);

    @Query("SELECT studentId FROM students WHERE user_name = :userName LIMIT 1")
    int getStudentIdByUserName(String userName);

    @Query("SELECT EXISTS(SELECT 1 FROM students WHERE user_name = :userName)")
    boolean doesStudentExist(String userName);
}
