package com.nhanlovecode.doancuoiky.Views.Order.OrderCompletedTab;

import com.nhanlovecode.doancuoiky.Models.Order;

import java.util.List;

public interface OrderCompletedTabMVPView {
    void getDataOrderCompletedSuccess(List<Order> orderList);

    void getDataOrderCompletedError();

    void searchDataOrderCompletedSuccess(List<Order> orderList);

    void searchDataOrderCompletedError();
}
