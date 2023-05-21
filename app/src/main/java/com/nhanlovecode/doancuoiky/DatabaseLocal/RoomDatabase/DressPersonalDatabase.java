package com.nhanlovecode.doancuoiky.DatabaseLocal.RoomDatabase;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nhanlovecode.doancuoiky.Models.DressPersonal;

@androidx.room.Database(entities = {DressPersonal.class} , version = 1)
public abstract class DressPersonalDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "DressPersonal.db";
    private static DressPersonalDatabase instance;

    public static synchronized DressPersonalDatabase getInstance(Context context){
        if (instance ==  null){
            instance = Room.databaseBuilder(context.getApplicationContext(),DressPersonalDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract DressPersonalDAO dressPersonalDAO();
}
