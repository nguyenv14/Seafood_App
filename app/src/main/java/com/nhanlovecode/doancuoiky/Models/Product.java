package com.nhanlovecode.doancuoiky.Models;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {

    int product_id;
    int category_id;
    String category_name;
    String product_name;
    String product_desc;
    Double product_price;
    String product_image;
    String product_unit;
    int product_unit_sold;
    int product_status;
    int product_viewer;
    String product_content;
    int product_quantity;
    String product_deliveryway;
    String product_origin;
    String product_delicious_foods;
    List<Comment> commentList;
    String status_order;
    int flashsale_status;
    String created_at;
    String updated_at;

    public Product(){};

    public Product(int product_id, int category_id, String category_name, String product_name, String product_desc, Double product_price, String product_image, String product_unit, int product_unit_sold, int product_status, int product_viewer, String product_content, int product_quantity, String product_deliveryway, String product_origin, String product_delicious_foods, List<Comment> commentList, String status_order, int flashsale_status, String created_at, String updated_at) {
        this.product_id = product_id;
        this.category_id = category_id;
        this.category_name = category_name;
        this.product_name = product_name;
        this.product_desc = product_desc;
        this.product_price = product_price;
        this.product_image = product_image;
        this.product_unit = product_unit;
        this.product_unit_sold = product_unit_sold;
        this.product_status = product_status;
        this.product_viewer = product_viewer;
        this.product_content = product_content;
        this.product_quantity = product_quantity;
        this.product_deliveryway = product_deliveryway;
        this.product_origin = product_origin;
        this.product_delicious_foods = product_delicious_foods;
        this.commentList = commentList;
        this.status_order = status_order;
        this.flashsale_status = flashsale_status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Product(int product_id, int category_id, String category_name, String product_name, String product_desc, Double product_price, String product_image) {
        this.product_id = product_id;
        this.category_id = category_id;
        this.category_name = category_name;
        this.product_name = product_name;
        this.product_desc = product_desc;
        this.product_price = product_price;
        this.product_image = product_image;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
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

    public String getProduct_unit() {
        return product_unit;
    }

    public void setProduct_unit(String product_unit) {
        this.product_unit = product_unit;
    }

    public int getProduct_unit_sold() {
        return product_unit_sold;
    }

    public void setProduct_unit_sold(int product_unit_sold) {
        this.product_unit_sold = product_unit_sold;
    }

    public int getProduct_status() {
        return product_status;
    }

    public void setProduct_status(int product_status) {
        this.product_status = product_status;
    }

    public int getProduct_viewer() {
        return product_viewer;
    }

    public void setProduct_viewer(int product_viewer) {
        this.product_viewer = product_viewer;
    }

    public String getProduct_content() {
        return product_content;
    }

    public void setProduct_content(String product_content) {
        this.product_content = product_content;
    }

    public int getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }

    public String getProduct_deliveryway() {
        return product_deliveryway;
    }

    public void setProduct_deliveryway(String product_deliveryway) {
        this.product_deliveryway = product_deliveryway;
    }

    public String getProduct_origin() {
        return product_origin;
    }

    public void setProduct_origin(String product_origin) {
        this.product_origin = product_origin;
    }

    public String getProduct_delicious_foods() {
        return product_delicious_foods;
    }

    public void setProduct_delicious_foods(String product_delicious_foods) {
        this.product_delicious_foods = product_delicious_foods;
    }

    public int getFlashsale_status() {
        return flashsale_status;
    }

    public void setFlashsale_status(int flashsale_status) {
        this.flashsale_status = flashsale_status;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public String getStatus_order() {
        return status_order;
    }

    public void setStatus_order(String status_order) {
        this.status_order = status_order;
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
}
