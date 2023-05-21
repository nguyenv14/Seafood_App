package com.nhanlovecode.doancuoiky.Views.Order.OrderProcessingTab;

import com.nhanlovecode.doancuoiky.Models.Order;

import java.util.List;

public interface OrderProcessingTabMVPView {
    void getDataOrderProcessingSuccess(List<Order> orderList);

    void getDataOrderProcessingError();

    void searchDataOrderProcessingSuccess(List<Order> orderList);

    void searchDataOrderProcessingError();

    void cancelOrderSuccess();
}
