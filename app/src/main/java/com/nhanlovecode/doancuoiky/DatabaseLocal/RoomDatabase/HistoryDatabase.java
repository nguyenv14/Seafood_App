package com.nhanlovecode.doancuoiky.DatabaseLocal.RoomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nhanlovecode.doancuoiky.Models.Favorite;
import com.nhanlovecode.doancuoiky.Models.History;

@Database(entities = {History.class}, version = 1)
public abstract class HistoryDatabase extends RoomDatabase {

    public abstract HistoryDAO historyDAO();

    public static HistoryDatabase getInstance(Context context){
        HistoryDatabase instance = null;
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), HistoryDatabase.class, "history.db").allowMainThreadQueries().build();
        }
        return instance;
    }
}
