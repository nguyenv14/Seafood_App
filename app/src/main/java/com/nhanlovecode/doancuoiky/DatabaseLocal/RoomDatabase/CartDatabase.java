package com.nhanlovecode.doancuoiky.DatabaseLocal.RoomDatabase;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nhanlovecode.doancuoiky.Models.Cart;

@androidx.room.Database(entities = {Cart.class} , version = 1)
public abstract class CartDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "cart.db";
    private static CartDatabase instance;

    public static synchronized CartDatabase getInstance(Context context){
        if (instance ==  null){
            instance = Room.databaseBuilder(context.getApplicationContext(),CartDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract CartDAO cartDAO();
}
