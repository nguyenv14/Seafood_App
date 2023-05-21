package com.nhanlovecode.doancuoiky.DatabaseLocal.RoomDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.nhanlovecode.doancuoiky.Models.History;
import com.nhanlovecode.doancuoiky.Models.ProductCart;

import java.util.List;
@Dao
public interface HistoryDAO {
    @Insert
    void insertHistoryProduct(History history);
    @Delete
    void deleteHistoryProduct(History history);

    @Query("SELECT * FROM history_product WHERE customer_id=:customer_id AND product_id=:product_id")
    History getHistoryProduct(int product_id, int customer_id);

    @Query("SELECT * FROM history_product WHERE customer_id=:customer_id")
    List<History> getHistoryProductList(int customer_id);
}
