package com.nhanlovecode.doancuoiky.Views.Personal.Evaluate.EvaluateWait;

import com.nhanlovecode.doancuoiky.Models.Order;

import java.util.List;

public interface EvaluateWaitMVPViews {
    void getEvaluateWaitSuccess(List<Order> data);

    void getEvaluateWaitError();
}
