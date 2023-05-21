package com.nhanlovecode.doancuoiky.ModelsAPI;

import com.nhanlovecode.doancuoiky.Models.City;

import java.util.List;

public class CityAPI {
    Integer status_code;
    String message;
    List<City> data;

    public CityAPI(){}

    public CityAPI(Integer status_code, String message, List<City> data) {
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

    public List<City> getData() {
        return data;
    }

    public void setData(List<City> data) {
        this.data = data;
    }
}
