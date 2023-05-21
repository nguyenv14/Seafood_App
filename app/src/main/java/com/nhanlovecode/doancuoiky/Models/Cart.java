package com.nhanlovecode.doancuoiky.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity(tableName = "Cart")
public class Cart implements Serializable   {
    @PrimaryKey(autoGenerate = true)
    int cart_id;
    @ColumnInfo(name = "product_id")
    int product_id;
    @ColumnInfo(name = "customer_id")
    int customer_id;
    @ColumnInfo(name = "product_name")
    String product_name;
    @ColumnInfo(name = "product_price")
    Double product_price;
    @ColumnInfo(name = "product_image")
    String product_image;
    @ColumnInfo(name = "product_quantity")
    int product_quantity;

    public Cart(int cart_id, int product_id, int customer_id, String product_name, Double product_price, String product_image, int product_quantity) {
        this.cart_id = cart_id;
        this.product_id = product_id;
        this.customer_id = customer_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_image = product_image;
        this.product_quantity = product_quantity;
    }
    @Ignore
    public Cart(int product_id, int customer_id, String product_name, Double product_price, String product_image, int product_quantity) {
        this.product_id = product_id;
        this.customer_id = customer_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_image = product_image;
        this.product_quantity = product_quantity;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
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

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
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

    public int getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Cart)) {
            return false;
        }
        Cart other = (Cart) obj;
        return Objects.equals(product_id, other.product_id) && Objects.equals(product_name, other.product_name);
    }

}
