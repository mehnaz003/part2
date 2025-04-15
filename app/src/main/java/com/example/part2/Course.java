// Entity class for Course

package com.example.part2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "courses",
        indices = {@Index(value = {"course_code"}, unique = true)})
public class Course {

    @PrimaryKey(autoGenerate = true)
    public int courseId;

    @NonNull
    @ColumnInfo(name = "course_code")
    public String courseCode;

    @NonNull
    public String courseName;

    @NonNull
    public String lecturerName;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @NonNull
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(@NonNull String courseCode) {
        this.courseCode = courseCode;
    }

    @NonNull
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(@NonNull String courseName) {
        this.courseName = courseName;
    }

    @NonNull
    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(@NonNull String lecturerName) {
        this.lecturerName = lecturerName;
    }
}