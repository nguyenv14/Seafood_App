package com.nhanlovecode.doancuoiky.ModelsAPI;

import com.nhanlovecode.doancuoiky.Models.Slider;

import java.util.List;

public class SliderAPI {

    Integer status_code;
    String message;
    List<Slider> data;

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

    public List<Slider> getData() {
        return data;
    }

    public void setData(List<Slider> data) {
        this.data = data;
    }
}
