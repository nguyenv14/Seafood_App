package com.nhanlovecode.doancuoiky.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DressPersonal")
public class DressPersonal {
    @PrimaryKey(autoGenerate = true)
    int dress_personal_id;
    @ColumnInfo(name = "customer_id")
    int customer_id;
    @ColumnInfo(name = "isChecked")
    Boolean isChecked;
    @ColumnInfo(name = "name_dress")
    String name_dress;
    @ColumnInfo(name = "shipping_name")
    String shipping_name;
    @ColumnInfo(name = "shipping_email")
    String shipping_email;
    @ColumnInfo(name = "shipping_phone")
    int shipping_phone;
    @ColumnInfo(name = "city_name")
    String city_name;
    @ColumnInfo(name = "province_name")
    String province_name;
    @ColumnInfo(name = "ward_name")
    String ward_name;
    @ColumnInfo(name = "home_number")
    String home_number;

    public DressPersonal(int customer_id, Boolean isChecked, String name_dress, String shipping_name, String shipping_email, int shipping_phone, String city_name, String province_name, String ward_name , String home_number) {
        this.customer_id = customer_id;
        this.isChecked = isChecked;
        this.name_dress = name_dress;
        this.shipping_name = shipping_name;
        this.shipping_email = shipping_email;
        this.shipping_phone = shipping_phone;
        this.city_name = city_name;
        this.province_name = province_name;
        this.ward_name = ward_name;
        this.home_number = home_number;
    }

    public int getDress_personal_id() {
        return dress_personal_id;
    }

    public void setDress_personal_id(int dress_personal_id) {
        this.dress_personal_id = dress_personal_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    public String getName_dress() {
        return name_dress;
    }

    public void setName_dress(String name_dress) {
        this.name_dress = name_dress;
    }

    public String getShipping_name() {
        return shipping_name;
    }

    public void setShipping_name(String shipping_name) {
        this.shipping_name = shipping_name;
    }

    public String getShipping_email() {
        return shipping_email;
    }

    public void setShipping_email(String shipping_email) {
        this.shipping_email = shipping_email;
    }

    public int getShipping_phone() {
        return shipping_phone;
    }

    public void setShipping_phone(int shipping_phone) {
        this.shipping_phone = shipping_phone;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getWard_name() {
        return ward_name;
    }

    public void setWard_name(String ward_name) {
        this.ward_name = ward_name;
    }

    public String getHome_number() {
        return home_number;
    }

    public void setHome_number(String home_number) {
        this.home_number = home_number;
    }
}
