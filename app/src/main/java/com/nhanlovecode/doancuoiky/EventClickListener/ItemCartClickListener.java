package com.nhanlovecode.doancuoiky.EventClickListener;

import com.nhanlovecode.doancuoiky.Models.Cart;

public interface ItemCartClickListener {
    void onClickDeleteItemCart(Cart cart, Boolean isChecked);

    void onClickPlusItemCart(Cart cart, Boolean isChecked);

    void onClickMinusItemCart(Cart cart, Boolean isChecked);

    void onClickCItemIsCheckedCart(Cart cart);

    void onClickCItemNoCheckedCart(Cart cart);
}
