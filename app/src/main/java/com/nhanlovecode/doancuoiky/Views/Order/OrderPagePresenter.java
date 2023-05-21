package com.nhanlovecode.doancuoiky.Views.Order;

import android.content.Context;

public class OrderPagePresenter {
    Context context;
    OrderPageMVPView mOrderPageMVPView;

    public OrderPagePresenter(Context context, OrderPageMVPView mOrderPageMVPView) {
        this.context = context;
        this.mOrderPageMVPView = mOrderPageMVPView;
    }
}
