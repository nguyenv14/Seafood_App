package com.nhanlovecode.doancuoiky.ModelsAPI;

import com.nhanlovecode.doancuoiky.Models.Category;
import com.nhanlovecode.doancuoiky.Models.Customer;

import java.util.List;

public class CustomerAPI {

    int status_code;
    String message;
    List<Customer> data;

    public CustomerAPI(int status_code, String message, List<Customer> data) {
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

    public List<Customer> getData() {
        return data;
    }

    public void setData(List<Customer> categoryList) {
        this.data = categoryList;
    }
}
