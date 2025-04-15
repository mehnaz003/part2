// Course with students data class for querying

package com.example.part2;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class CourseWithStudents {

    @Embedded
    public Course course;

    @Relation(
            parentColumn = "courseId",
            entityColumn = "studentId",
            associateBy = @Junction(CourseStudent.class)
    )
    public List<Student> students;
}
