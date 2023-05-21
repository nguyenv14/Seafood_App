package com.nhanlovecode.doancuoiky.DatabaseLocal.RoomDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.nhanlovecode.doancuoiky.Models.DressPersonal;

import java.util.List;

@Dao
public interface DressPersonalDAO {

    @Insert
    void InsertDressPersonal(DressPersonal dressPersonal);

    @Query("SELECT * FROM DressPersonal WHERE customer_id=:customer_id ORDER BY isChecked DESC")
    List<DressPersonal> getListDressPersonal(int customer_id);

    @Query("SELECT * FROM DressPersonal Where dress_personal_id = :dress_personal_id" )
    List<DressPersonal> checkDressPersonal(int dress_personal_id);

    @Update
    void UpdateDressPersonal(DressPersonal dressPersonal);

    @Delete
    void DeleteDressPersonal(DressPersonal dressPersonal);

    @Query("DELETE FROM DressPersonal")
    void DeleteAllCart();

}
