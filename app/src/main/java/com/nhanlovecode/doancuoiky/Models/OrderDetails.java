package com.nhanlovecode.doancuoiky.Models;

import java.io.Serializable;

public class OrderDetails implements Serializable {
    int order_details_id;
    String order_code;
    int product_id;
    String product_name;
    Double product_price;
    String product_image;
    int category_id;
    String category_name;
    int product_sales_quantity;
    String created_at;
    String updated_at;


    public OrderDetails(int order_details_id, String order_code, int product_id, String product_name, Double product_price, String product_image, int category_id, String category_name, int product_sales_quantity, String created_at, String updated_at) {
        this.order_details_id = order_details_id;
        this.order_code = order_code;
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_image = product_image;
        this.category_id = category_id;
        this.category_name = category_name;
        this.product_sales_quantity = product_sales_quantity;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getOrder_details_id() {
        return order_details_id;
    }

    public void setOrder_details_id(int order_details_id) {
        this.order_details_id = order_details_id;
    }

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
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

    public Double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Double product_price) {
        this.product_price = product_price;
    }

    public int getProduct_sales_quantity() {
        return product_sales_quantity;
    }

    public void setProduct_sales_quantity(int product_sales_quantity) {
        this.product_sales_quantity = product_sales_quantity;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
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
}
