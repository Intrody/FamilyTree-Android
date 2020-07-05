package com.example.familytree.com.example.mem;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Mem.class},version = 1,exportSchema = false)
public abstract class MemDatabase extends RoomDatabase {
    private static MemDatabase INSTANCE;

    static synchronized MemDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MemDatabase.class, "mem_database")
                    .build();

        }
        return INSTANCE;
    }
    public  abstract MemDao getMemDao();
}
