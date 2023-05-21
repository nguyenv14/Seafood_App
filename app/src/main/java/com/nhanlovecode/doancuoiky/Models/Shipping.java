package com.nhanlovecode.doancuoiky.Models;

import java.io.Serializable;

public class Shipping implements Serializable {
    int shipping_id;
    String shipping_name;
    String shipping_email;
    int shipping_phone;
    String shipping_address;
    String shipping_notes;
    int shipping_special_requirements;
    int shipping_receipt;
    String created_at;
    String updated_at;

    public Shipping(){}

    public Shipping(int shipping_id, String shipping_name, String shipping_email, int shipping_phone, String shipping_address, String shipping_notes, int shipping_special_requirements, int shipping_receipt, String created_at, String updated_at) {
        this.shipping_id = shipping_id;
        this.shipping_name = shipping_name;
        this.shipping_email = shipping_email;
        this.shipping_phone = shipping_phone;
        this.shipping_address = shipping_address;
        this.shipping_notes = shipping_notes;
        this.shipping_special_requirements = shipping_special_requirements;
        this.shipping_receipt = shipping_receipt;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Shipping(String shipping_name, String shipping_email, int shipping_phone, String shipping_address, String shipping_notes, int shipping_special_requirements, int shipping_receipt) {
        this.shipping_name = shipping_name;
        this.shipping_email = shipping_email;
        this.shipping_phone = shipping_phone;
        this.shipping_address = shipping_address;
        this.shipping_notes = shipping_notes;
        this.shipping_special_requirements = shipping_special_requirements;
        this.shipping_receipt = shipping_receipt;
    }

    public int getShipping_id() {
        return shipping_id;
    }

    public void setShipping_id(int shipping_id) {
        this.shipping_id = shipping_id;
    }

    public String getShipping_name() {
        return shipping_name;
    }

    public void setShipping_name(String shipping_name) {
        this.shipping_name = shipping_name;
    }

    public String getShipping_email() {
        return shipping_email;
    }

    public void setShipping_email(String shipping_email) {
        this.shipping_email = shipping_email;
    }

    public int getShipping_phone() {
        return shipping_phone;
    }

    public void setShipping_phone(int shipping_phone) {
        this.shipping_phone = shipping_phone;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }

    public String getShipping_notes() {
        return shipping_notes;
    }

    public void setShipping_notes(String shipping_notes) {
        this.shipping_notes = shipping_notes;
    }

    public int getShipping_special_requirements() {
        return shipping_special_requirements;
    }

    public void setShipping_special_requirements(int shipping_special_requirements) {
        this.shipping_special_requirements = shipping_special_requirements;
    }

    public int getShipping_receipt() {
        return shipping_receipt;
    }

    public void setShipping_receipt(int shipping_receipt) {
        this.shipping_receipt = shipping_receipt;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
