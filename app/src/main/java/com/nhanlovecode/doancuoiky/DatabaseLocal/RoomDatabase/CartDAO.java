package com.nhanlovecode.doancuoiky.DatabaseLocal.RoomDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.nhanlovecode.doancuoiky.Models.Cart;

import java.util.List;

@Dao
public interface CartDAO {

    @Insert
    void InsertCart(Cart cart);

    @Query("SELECT * FROM Cart WHERE customer_id=:customer_id")
    List<Cart> getListCart(int customer_id);

    @Query("SELECT * FROM Cart Where product_id = :product_id")
    List<Cart> checkCart(int product_id);

    @Update
    void UpdateCart(Cart cart);

    @Delete
    void DeleteCart(Cart cart);


    @Query("DELETE FROM Cart")
    void DeleteAllCart();

    @Query("SELECT * FROM Cart WHERE product_name LIKE '%' || :product_name || '%'  ")
    List<Cart> searchCart(String product_name);

}
