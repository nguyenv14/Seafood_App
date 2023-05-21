package com.nhanlovecode.doancuoiky.Views.Order.OrderCancelledTab;

import com.nhanlovecode.doancuoiky.Models.Order;

import java.util.List;

public interface OrderCancelledTabMVPView {
    void getDataOrderCancelledSuccess(List<Order> orderList);

    void getDataOrderCancelledError();

    void searchDataOrderCancelledSuccess(List<Order> orderList);

    void searchDataOrderCancelledError();
}
