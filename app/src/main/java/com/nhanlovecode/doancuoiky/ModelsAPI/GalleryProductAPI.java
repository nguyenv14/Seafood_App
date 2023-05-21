package com.nhanlovecode.doancuoiky.ModelsAPI;

import com.nhanlovecode.doancuoiky.Models.GalleryProduct;

import java.util.List;

public class GalleryProductAPI {
    List<GalleryProduct> data;
    Integer status_code;
    String message;

    public Integer getStatus_code() {
        return status_code;
    }

    public void setStatus_code(Integer status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<GalleryProduct> getData() {
        return data;
    }

    public void setData(List<GalleryProduct> data) {
        this.data = data;
    }
}
