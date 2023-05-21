package com.nhanlovecode.doancuoiky.Views.Order.OrderDeliveringTab;

import com.nhanlovecode.doancuoiky.Models.Order;

import java.util.List;

public interface OrderDeliveringTabMVPView {
    void getDataOrderDeliveringSuccess(List<Order> orderList);

    void getDataOrderDeliveringError();

    void searchDataOrderDeliveringSuccess(List<Order> orderList);

    void searchDataOrderDeliveringError();

    void getReceiveOrderSuccess();
}
