package com.nhanlovecode.doancuoiky.EventClickListener;

import com.nhanlovecode.doancuoiky.Models.ProductCart;

public interface ItemProductCartListener {

    void deleteProductCart(ProductCart productCart);

    void checkedProductCart(ProductCart productCart);

    void unCheckedProductCart(ProductCart productCart);

    void calculateQuantityAndPrice(int status, ProductCart productCart);


}
