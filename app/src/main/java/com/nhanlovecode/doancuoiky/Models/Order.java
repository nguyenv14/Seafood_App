package com.nhanlovecode.doancuoiky.Models;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    int order_id;
    int customer_id;
    int shipping_id;
    int payment_id;
    int order_status;
    String order_code;
    int product_fee;
    String product_coupon;
    int product_price_coupon;
    Double total_price;
    int total_quantity;
    String order_date;
    Shipping shipping;
    Payment payment;
    List<OrderDetails> orderDetails;
    String 	created_at;
    String updated_at;


    public Order(int order_id, int customer_id, int shipping_id, int payment_id, int order_status, String order_code, int product_fee, String product_coupon, int product_price_coupon, Double total_price, int total_quantity, String order_date, Shipping shipping, Payment payment, List<OrderDetails> orderDetails, String created_at, String updated_at) {
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.shipping_id = shipping_id;
        this.payment_id = payment_id;
        this.order_status = order_status;
        this.order_code = order_code;
        this.product_fee = product_fee;
        this.product_coupon = product_coupon;
        this.product_price_coupon = product_price_coupon;
        this.total_price = total_price;
        this.total_quantity = total_quantity;
        this.order_date = order_date;
        this.shipping = shipping;
        this.payment = payment;
        this.orderDetails = orderDetails;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getShipping_id() {
        return shipping_id;
    }

    public void setShipping_id(int shipping_id) {
        this.shipping_id = shipping_id;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public int getProduct_fee() {
        return product_fee;
    }

    public void setProduct_fee(int product_fee) {
        this.product_fee = product_fee;
    }

    public String getProduct_coupon() {
        return product_coupon;
    }

    public void setProduct_coupon(String product_coupon) {
        this.product_coupon = product_coupon;
    }

    public int getProduct_price_coupon() {
        return product_price_coupon;
    }

    public void setProduct_price_coupon(int product_price_coupon) {
        this.product_price_coupon = product_price_coupon;
    }

    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }

    public int getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(int total_quantity) {
        this.total_quantity = total_quantity;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
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
