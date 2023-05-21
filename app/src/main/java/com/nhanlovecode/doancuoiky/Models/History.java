package com.nhanlovecode.doancuoiky.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "history_product")
public class History {
    @PrimaryKey(autoGenerate = true)
    int history_id;
    @ColumnInfo(name = "product_id")
    int product_id;
    @ColumnInfo(name = "customer_id")
    int customer_id;
    @ColumnInfo(name = "category_id")
    int category_id;
    @ColumnInfo(name = "category_name")
    String category_name;
    @ColumnInfo(name = "product_name")
    String product_name;
    @ColumnInfo(name = "product_desc")
    String product_desc;
    @ColumnInfo(name = "product_price")
    Double product_price;
    @ColumnInfo(name = "product_image")
    String product_image;

    public History(int history_id, int product_id, int customer_id, int category_id, String category_name, String product_name, String product_desc, Double product_price, String product_image) {
        this.history_id = history_id;
        this.product_id = product_id;
        this.customer_id = customer_id;
        this.category_id = category_id;
        this.category_name = category_name;
        this.product_name = product_name;
        this.product_desc = product_desc;
        this.product_price = product_price;
        this.product_image = product_image;
    }
    @Ignore
    public History(int product_id, int customer_id, int category_id, String category_name, String product_name, String product_desc, Double product_price, String product_image) {
        this.product_id = product_id;
        this.customer_id = customer_id;
        this.category_id = category_id;
        this.category_name = category_name;
        this.product_name = product_name;
        this.product_desc = product_desc;
        this.product_price = product_price;
        this.product_image = product_image;
    }

    public int getHistory_id() {
        return history_id;
    }

    public void setHistory_id(int history_id) {
        this.history_id = history_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public Double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Double product_price) {
        this.product_price = product_price;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

}
