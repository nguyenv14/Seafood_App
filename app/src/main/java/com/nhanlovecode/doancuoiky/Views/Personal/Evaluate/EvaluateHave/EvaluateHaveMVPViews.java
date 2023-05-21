package com.nhanlovecode.doancuoiky.Views.Personal.Evaluate.EvaluateHave;

import com.nhanlovecode.doancuoiky.Models.Order;

import java.util.List;

public interface EvaluateHaveMVPViews {
    void getEvaluateHaveSuccess(List<Order> orderList);
    void getEvaluateHaveError();
}
