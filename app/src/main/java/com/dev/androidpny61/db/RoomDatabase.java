package com.dev.androidpny61.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

@Database(entities = {ContactEntity.class}, version = 1)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {

    public abstract ContactDao contactDao();

    private static RoomDatabase roomDatabase;

    public static RoomDatabase getInstance(Context context){

        if(roomDatabase == null){
            String myDBName = "my-database";
            roomDatabase =  Room.databaseBuilder(
                    context, // calling class reference
                    RoomDatabase.class, // RoomDatabase
                    myDBName) // database name
                    .allowMainThreadQueries()
                    .build();
        }

        return roomDatabase;
    }





}
