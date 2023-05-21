package com.nhanlovecode.doancuoiky.Models;

public class Coupon {
    int coupon_id;
    String coupon_name;
    String coupon_name_code;
    String coupon_desc;
    int coupon_qty_code;
    int coupon_condition;
    double coupon_price_sale;
    String coupon_start_date;
    String coupon_end_date;
    String created_at;
    String updated_at;

    public Coupon(int coupon_id, String coupon_name, String coupon_name_code, String coupon_desc, int coupon_qty_code, int coupon_condition, double coupon_price_sale, String coupon_start_date, String coupon_end_date, String created_at, String updated_at) {
        this.coupon_id = coupon_id;
        this.coupon_name = coupon_name;
        this.coupon_name_code = coupon_name_code;
        this.coupon_desc = coupon_desc;
        this.coupon_qty_code = coupon_qty_code;
        this.coupon_condition = coupon_condition;
        this.coupon_price_sale = coupon_price_sale;
        this.coupon_start_date = coupon_start_date;
        this.coupon_end_date = coupon_end_date;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(int coupon_id) {
        this.coupon_id = coupon_id;
    }

    public String getCoupon_name() {
        return coupon_name;
    }

    public void setCoupon_name(String coupon_name) {
        this.coupon_name = coupon_name;
    }

    public String getCoupon_name_code() {
        return coupon_name_code;
    }

    public void setCoupon_name_code(String coupon_name_code) {
        this.coupon_name_code = coupon_name_code;
    }

    public String getCoupon_desc() {
        return coupon_desc;
    }

    public void setCoupon_desc(String coupon_desc) {
        this.coupon_desc = coupon_desc;
    }

    public int getCoupon_qty_code() {
        return coupon_qty_code;
    }

    public void setCoupon_qty_code(int coupon_qty_code) {
        this.coupon_qty_code = coupon_qty_code;
    }

    public int getCoupon_condition() {
        return coupon_condition;
    }

    public void setCoupon_condition(int coupon_condition) {
        this.coupon_condition = coupon_condition;
    }

    public double getCoupon_price_sale() {
        return coupon_price_sale;
    }

    public void setCoupon_price_sale(double coupon_price_sale) {
        this.coupon_price_sale = coupon_price_sale;
    }

    public String getCoupon_start_date() {
        return coupon_start_date;
    }

    public void setCoupon_start_date(String coupon_start_date) {
        this.coupon_start_date = coupon_start_date;
    }

    public String getCoupon_end_date() {
        return coupon_end_date;
    }

    public void setCoupon_end_date(String coupon_end_date) {
        this.coupon_end_date = coupon_end_date;
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
