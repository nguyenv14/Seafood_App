package com.nhanlovecode.doancuoiky.Models;

import java.io.Serializable;

public class Payment implements Serializable {
    int payment_id;
    int payment_method;
    int payment_status;

    public Payment(){}

    public Payment(int payment_id, int payment_method, int payment_status) {
        this.payment_id = payment_id;
        this.payment_method = payment_method;
        this.payment_status = payment_status;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public int getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(int payment_method) {
        this.payment_method = payment_method;
    }

    public int getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(int payment_status) {
        this.payment_status = payment_status;
    }
}
