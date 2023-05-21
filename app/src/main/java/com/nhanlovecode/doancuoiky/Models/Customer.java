package com.nhanlovecode.doancuoiky.Models;

import java.io.Serializable;

public class Customer implements Serializable {
    int customer_id;
    String customer_name;
    Long customer_phone;
    String customer_email;
    String customer_password;

    public Customer(String customer_name, Long customer_phone, String customer_email, String customer_password) {
        this.customer_name = customer_name;
        this.customer_phone = customer_phone;
        this.customer_email = customer_email;
        this.customer_password = customer_password;
    }

    public Customer(int customer_id, String customer_name, Long customer_phone, String customer_email, String customer_password) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.customer_phone = customer_phone;
        this.customer_email = customer_email;
        this.customer_password = customer_password;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public Long getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(Long customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_password() {
        return customer_password;
    }

    public void setCustomer_password(String customer_password) {
        this.customer_password = customer_password;
    }
}
