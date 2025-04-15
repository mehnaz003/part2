// Student with courses data class for querying

package com.example.part2;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class StudentWithCourses {

    @Embedded
    public Student student;

    @Relation(
            parentColumn = "studentId",
            entityColumn = "courseId",
            associateBy = @Junction(CourseStudent.class)
    )
    public List<Course> courses;
}
