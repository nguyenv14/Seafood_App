package com.nhanlovecode.doancuoiky.ModelsAPI;

import com.nhanlovecode.doancuoiky.Models.Coupon;

import java.util.List;

public class CouponAPI {
    Integer status_code;
    String message;
    List<Coupon> data;

    public CouponAPI(Integer status_code, String message, List<Coupon> data) {
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

    public List<Coupon> getData() {
        return data;
    }

    public void setData(List<Coupon> data) {
        this.data = data;
    }
}
