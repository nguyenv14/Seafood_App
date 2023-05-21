package com.nhanlovecode.doancuoiky.Models;

import java.io.Serializable;

public class Comment implements Serializable {
    int comment_id;

    String order_code;
    String comment_title;
    String comment_content;
    int comment_customer_id;
    String comment_customer_name;
    int comment_product_id;
    int comment_rate_star;

    String comment_date;


    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public String getComment_date() {
        return comment_date;
    }

    public void setComment_date(String comment_date) {
        this.comment_date = comment_date;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getComment_title() {
        return comment_title;
    }

    public void setComment_title(String comment_title) {
        this.comment_title = comment_title;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public int getComment_customer_id() {
        return comment_customer_id;
    }

    public void setComment_customer_id(int comment_customer_id) {
        this.comment_customer_id = comment_customer_id;
    }

    public String getComment_customer_name() {
        return comment_customer_name;
    }

    public void setComment_customer_name(String comment_customer_name) {
        this.comment_customer_name = comment_customer_name;
    }

    public int getComment_product_id() {
        return comment_product_id;
    }

    public void setComment_product_id(int comment_product_id) {
        this.comment_product_id = comment_product_id;
    }

    public int getComment_rate_star() {
        return comment_rate_star;
    }

    public void setComment_rate_star(int comment_rate_star) {
        this.comment_rate_star = comment_rate_star;
    }
}
