package com.nhanlovecode.doancuoiky.Views.Cart;

import android.content.Context;

import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseLocal.RoomDatabase.CartDatabase;
import com.nhanlovecode.doancuoiky.DatabaseLocal.SharedPreferences.MySharedPreferencesManager;
import com.nhanlovecode.doancuoiky.Models.Cart;

import java.util.List;

public class CartPresenter {
    Context context;
    CartMVPView mCartMVPView;
    int customer_id = MySharedPreferencesManager.getCustomer(Constant.PREF_KEY_CUSTOMER).getCustomer_id();

    public CartPresenter(Context context, CartMVPView mCartMVPView) {
        this.context = context;
        this.mCartMVPView = mCartMVPView;
    }

    public void getDataCart() {
        List<Cart> cartList = CartDatabase.getInstance(context).cartDAO().getListCart(customer_id);
        if(cartList != null){
            mCartMVPView.getDataCartSuccess(cartList);
        }else {
            mCartMVPView.getDataCartError();
        }
    }

    public void loadCart() {
        List<Cart> cartList = CartDatabase.getInstance(context).cartDAO().getListCart(customer_id);
        mCartMVPView.loadDataCartSuccess(cartList);
    }

    public void deleteItemCart(Cart cart) {
        CartDatabase.getInstance(context).cartDAO().DeleteCart(cart);
        mCartMVPView.deleteItemCartSuccess();
    }

    public void plusItemCart(Cart cart) {
        if(cart.getProduct_quantity() >= 100){
            mCartMVPView.plusItemCartError();
        }else {
            int product_quantity = cart.getProduct_quantity() + 1;
            cart.setProduct_quantity(product_quantity);
            CartDatabase.getInstance(context).cartDAO().UpdateCart(cart);
            mCartMVPView.plusItemCartSuccess();
        }
    }

    public void minusItemCart(Cart cart) {
        if (cart.getProduct_quantity() <= 1) {
            mCartMVPView.minusItemCartError();
        } else {
            int product_quantity = cart.getProduct_quantity() - 1;
            cart.setProduct_quantity(product_quantity);
            CartDatabase.getInstance(context).cartDAO().UpdateCart(cart);
            mCartMVPView.minusItemCartSuccess();
        }
    }

    public void checkedAll() {
        List<Cart> cartList = CartDatabase.getInstance(context).cartDAO().getListCart(customer_id);
        mCartMVPView.checkedAllSuccess(cartList);
    }

    public void unCheckedAll() {
        mCartMVPView.unCheckedAllSuccess();
    }

    public void calTotalAmount(List<Cart> cartListChecked) {
        Double totalAmount = 0.0;
        for (int i = 0 ; i < cartListChecked.size() ; i++){
            totalAmount += cartListChecked.get(i).getProduct_price() * cartListChecked.get(i).getProduct_quantity();
        }
        mCartMVPView.calTotalAmountSuccess(totalAmount);

    }

    public void plusItemCartChecked(List<Cart> cartListChecked, Cart cart) {
        for (Cart cartList : cartListChecked) {
            if (cartList.getCart_id() == cart.getCart_id()) {
                if (cartList.getProduct_quantity() > 1){
                    cartList.setProduct_quantity(cartList.getProduct_quantity() + 1) ;
                    break;
                }
                break;
            }
        }
        mCartMVPView.plusItemCartCheckedSuccess(cartListChecked);
    }

    public void minusItemCartChecked(List<Cart> cartListChecked, Cart cart) {
        for (Cart cartList : cartListChecked) {
            if (cartList.getCart_id() == cart.getCart_id()) {
                if (cartList.getProduct_quantity() > 1){
                    cartList.setProduct_quantity(cartList.getProduct_quantity() - 1) ;
                    break;
                }
            }
        }
        mCartMVPView.minusItemCartCheckedSuccess(cartListChecked);
    }

    public void getStateCheckBoxAll(List<Cart> mCartListChecked) {
        List<Cart> cartList = CartDatabase.getInstance(context).cartDAO().getListCart(customer_id);
        if(cartList.size() == mCartListChecked.size()){
            mCartMVPView.stateCheckBoxFull();
        }else {
            mCartMVPView.stateCheckBoxNotFull();
        }
    }

    public void deleteItemCartChecked(List<Cart> mCartListChecked, Cart cart) {
        mCartListChecked.remove(cart);
        mCartMVPView.deleteItemCartCheckedSuccess(mCartListChecked);
    }
}
