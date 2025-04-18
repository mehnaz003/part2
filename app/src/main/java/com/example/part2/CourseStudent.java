// Junction table for many to many relationship between Course and Student

package com.example.part2;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(tableName = "course_student",
        primaryKeys = {"courseId", "studentId"},
        foreignKeys = {
                @ForeignKey(
                        entity = Course.class,
                        parentColumns = "courseId", childColumns = "courseId",
                        onDelete = ForeignKey.CASCADE
                ),

                @ForeignKey(
                        entity = Student.class,
                        parentColumns = "studentId", childColumns = "studentId",
                        onDelete = ForeignKey.CASCADE
                )
        },
        indices = {
            @Index("courseId"),
            @Index("studentId")
        }
)

public class CourseStudent {

    public int courseId;
    public int studentId;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
