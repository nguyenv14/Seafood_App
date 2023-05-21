package com.nhanlovecode.doancuoiky.DatabaseLocal.RoomDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.nhanlovecode.doancuoiky.Models.Favorite;

import java.util.List;
@Dao
public interface FavoriteDAO {
    @Insert
    void insertFavorite(Favorite favorite);
    @Delete
    void deleteFavorite(Favorite favorite);
//    @Update
//    void updateProductCart(ProductCart productCart);

    @Query("SELECT * FROM favorite WHERE customer_id= :customer_id")
    List<Favorite> getListFavorite(int customer_id);

    @Query("SELECT * FROM favorite WHERE customer_id=:customer_id AND product_id=:product_id")
    Favorite getFavorite(int product_id, int customer_id);

    @Query("SELECT * FROM favorite WHERE favorite_id=:favorite_id")
    Favorite getFavoriteByFavoriteId(int favorite_id);
}
