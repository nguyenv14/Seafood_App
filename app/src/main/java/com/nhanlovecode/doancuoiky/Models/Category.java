package com.nhanlovecode.doancuoiky.Models;

import java.io.Serializable;

public class Category implements Serializable {
    int category_id;
    String category_name;
    String category_image;
    String category_desc;
    String category_status;
    String created_at;
    String updated_at;

    public Category(int category_id, String category_name, String category_image, String category_desc, String category_status, String created_at, String updated_at) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.category_image = category_image;
        this.category_desc = category_desc;
        this.category_status = category_status;
        this.created_at = created_at;
        this.updated_at = updated_at;
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

    public String getCategory_image() {
        return category_image;
    }

    public void setCategory_image(String category_image) {
        this.category_image = category_image;
    }

    public String getCategory_desc() {
        return category_desc;
    }

    public void setCategory_desc(String category_desc) {
        this.category_desc = category_desc;
    }

    public String getCategory_status() {
        return category_status;
    }

    public void setCategory_status(String category_status) {
        this.category_status = category_status;
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
