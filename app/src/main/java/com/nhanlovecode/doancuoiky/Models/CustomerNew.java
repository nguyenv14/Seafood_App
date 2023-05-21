package com.nhanlovecode.doancuoiky.Models;

import java.io.Serializable;

public class CustomerNew implements Serializable {

    String customer_email;
    int code;
    int status;

    public CustomerNew(String customer_email, int code, int status) {

        this.customer_email = customer_email;
        this.code = code;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
