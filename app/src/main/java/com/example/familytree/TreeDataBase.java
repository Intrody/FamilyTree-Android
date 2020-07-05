package com.example.familytree;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Tree.class},version = 1,exportSchema = false)
public abstract class TreeDataBase extends RoomDatabase {
    private static TreeDataBase INSTANCE;
    static synchronized TreeDataBase getDatabase(Context context){
        if(INSTANCE ==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),TreeDataBase.class,"tree_database")
                    .build();
        }
        return INSTANCE;
    }
    public abstract TreeDao getTreeDao();
}
