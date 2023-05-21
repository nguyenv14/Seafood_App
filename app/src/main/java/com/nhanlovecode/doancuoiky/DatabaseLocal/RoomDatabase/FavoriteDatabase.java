package com.nhanlovecode.doancuoiky.DatabaseLocal.RoomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nhanlovecode.doancuoiky.Models.Favorite;

@Database(entities = {Favorite.class}, version = 1)
public abstract class FavoriteDatabase extends RoomDatabase {

    public abstract FavoriteDAO favoriteDAO();

    public static FavoriteDatabase getInstance(Context context){
        FavoriteDatabase instance = null;
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), FavoriteDatabase.class, "favorite.db").allowMainThreadQueries().build();
        }
        return instance;
    }
}
