package com.nhanlovecode.doancuoiky.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_cart")
public class ProductCart {
    @PrimaryKey(autoGenerate = true)
    public int cart_id;
    @ColumnInfo(name = "customer_id")
    public int customer_id;
    @ColumnInfo(name = "product_id")
    public int product_id;
    @ColumnInfo(name = "product_name")
    public String product_name;
    @ColumnInfo(name = "category_id")
    public int category_id;
    @ColumnInfo(name = "category_name")
    public String category_name;
    @ColumnInfo(name = "product_desc")
    public String product_desc;
    @ColumnInfo(name = "product_price")
    public int product_price;
    @ColumnInfo(name = "product_price_subtotal")
    public int product_price_subtotal;
    @ColumnInfo(name = "product_quantity")
    public int product_quantity;
    @ColumnInfo(name = "product_image")
    public String product_image;

    public ProductCart(int cart_id, int customer_id, int product_id, String product_name, int category_id, String category_name, String product_desc, int product_price, int product_price_subtotal, int product_quantity, String product_image) {
        this.cart_id = cart_id;
        this.customer_id = customer_id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.category_id = category_id;
        this.category_name = category_name;
        this.product_desc = product_desc;
        this.product_price = product_price;
        this.product_price_subtotal = product_price_subtotal;
        this.product_quantity = product_quantity;
        this.product_image = product_image;
    }
    @Ignore
    public ProductCart(int customer_id, int product_id, String product_name, int category_id, String category_name, String product_desc, int product_price, int product_price_subtotal, int product_quantity, String product_image) {
        this.customer_id = customer_id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.category_id = category_id;
        this.category_name = category_name;
        this.product_desc = product_desc;
        this.product_price = product_price;
        this.product_price_subtotal = product_price_subtotal;
        this.product_quantity = product_quantity;
        this.product_image = product_image;
    }

    public int getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
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

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
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

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public int getProduct_price_subtotal() {
        return product_price_subtotal;
    }

    public void setProduct_price_subtotal(int product_price_subtotal) {
        this.product_price_subtotal = product_price_subtotal;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }
}
