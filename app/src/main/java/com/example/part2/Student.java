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

    @NonNull
    public String name;

    @NonNull
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

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
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