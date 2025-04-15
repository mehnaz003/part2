// Course Management System Database

package com.example.part2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(
        entities = {
                Course.class,
                Student.class,
                CourseStudent.class
        },
        version = 1, exportSchema = false
)
public abstract class SystemDB extends RoomDatabase {

    public abstract CourseDao courseDao();

    public abstract StudentDao studentDao();

    private static volatile SystemDB INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static SystemDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SystemDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    SystemDB.class, "system_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}