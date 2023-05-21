package com.nhanlovecode.doancuoiky.ModelsAPI;

import com.nhanlovecode.doancuoiky.Models.CustomerNew;

import java.util.List;

public class CustomerNewAPI {
    int status_code;
    String message;
    List<CustomerNew> data;

    public CustomerNewAPI(int status_code, String message, List<CustomerNew> data) {
        this.status_code = status_code;
        this.message = message;
        this.data = data;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CustomerNew> getData() {
        return data;
    }

    public void setData(List<CustomerNew> data) {
        this.data = data;
    }
}
