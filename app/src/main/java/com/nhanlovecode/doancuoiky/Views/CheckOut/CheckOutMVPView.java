package com.nhanlovecode.doancuoiky.Views.CheckOut;

import com.nhanlovecode.doancuoiky.Models.Coupon;
import com.nhanlovecode.doancuoiky.Models.DressPersonal;
import com.nhanlovecode.doancuoiky.Models.Order;

public interface CheckOutMVPView {
    void getDataAddressPersonalSuccess(DressPersonal dressPersonal);

    void getDataAddressPersonalError();

    void checkCouponError();

    void checkCouponSuccess(Coupon coupon);

    void calculatingTotalProgressSuccess(Double totalPrice, Double deliveringFee, Double couponPriceSale, Double pricePayment);

    void putOrderError(int typeError);

    void putOrderSuccess(Order order);

    void resetDataSuccess(Order order);
}
