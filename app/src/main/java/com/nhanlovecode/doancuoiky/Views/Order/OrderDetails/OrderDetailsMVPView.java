package com.nhanlovecode.doancuoiky.Views.Order.OrderDetails;

import com.nhanlovecode.doancuoiky.Models.Comment;
import com.nhanlovecode.doancuoiky.Models.Order;

public interface OrderDetailsMVPView {
    void getDataOrderSuccess(Order order);

    void getDataOrderErorr();

    void getEvaluateOrderSuccess(Comment comment);

    void getEvaluateOrderNull();
}
