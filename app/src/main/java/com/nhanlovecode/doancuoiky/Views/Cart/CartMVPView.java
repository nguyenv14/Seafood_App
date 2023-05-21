package com.nhanlovecode.doancuoiky.Views.Cart;

import com.nhanlovecode.doancuoiky.Models.Cart;

import java.util.List;

public interface CartMVPView {
    void getDataCartSuccess(List<Cart> cartList);

    void getDataCartError();

    void loadDataCartSuccess(List<Cart> cartList);

    void deleteItemCartSuccess();

    void minusItemCartSuccess();

    void minusItemCartError();

    void plusItemCartSuccess();

    void plusItemCartError();

    void checkedAllSuccess(List<Cart> cartList);

    void unCheckedAllSuccess();

    void calTotalAmountSuccess(Double totalAmount);

    void minusItemCartCheckedSuccess(List<Cart> cartListChecked);

    void plusItemCartCheckedSuccess(List<Cart> cartListChecked);

    void stateCheckBoxFull();

    void stateCheckBoxNotFull();

    void deleteItemCartCheckedSuccess(List<Cart> mCartListChecked);
}
