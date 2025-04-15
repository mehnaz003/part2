// Entity class for Student

package com.example.part2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "students",
        indices = {@Index(value = {"user_name"}, unique = true)})
public class Student {

    @PrimaryKey(autoGenerate = true)
    public int studentId;

    public String name;
    public String email;

    @NonNull
    @ColumnInfo(name = "user_name")
    public String userName;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }
}