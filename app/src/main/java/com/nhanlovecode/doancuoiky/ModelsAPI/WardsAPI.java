package com.nhanlovecode.doancuoiky.ModelsAPI;

import com.nhanlovecode.doancuoiky.Models.Wards;

import java.util.List;

public class WardsAPI {
    Integer status_code;
    String message;
    List<Wards> data;

    public WardsAPI(Integer status_code, String message, List<Wards> data) {
        this.status_code = status_code;
        this.message = message;
        this.data = data;
    }

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

    public List<Wards> getData() {
        return data;
    }

    public void setData(List<Wards> data) {
        this.data = data;
    }
}
