package com.nhanlovecode.doancuoiky.Views.CheckOut;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseLocal.RoomDatabase.CartDatabase;
import com.nhanlovecode.doancuoiky.DatabaseLocal.RoomDatabase.DressPersonalDatabase;
import com.nhanlovecode.doancuoiky.DatabaseServer.RepositoryAPI;
import com.nhanlovecode.doancuoiky.DatabaseServer.RetrofitClient;
import com.nhanlovecode.doancuoiky.Models.Cart;
import com.nhanlovecode.doancuoiky.Models.Coupon;
import com.nhanlovecode.doancuoiky.Models.DressPersonal;
import com.nhanlovecode.doancuoiky.Models.Order;
import com.nhanlovecode.doancuoiky.Models.Payment;
import com.nhanlovecode.doancuoiky.Models.Shipping;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import vn.momo.momo_partner.AppMoMoLib;


public class CheckOutPresenter {
    CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    RepositoryAPI mRepositoryAPI = RetrofitClient.getInstance(Constant.BASE_URL).create(RepositoryAPI.class);

    int contextMM;
    Activity currentActivity;
    Context context;
    CheckOutMVPView mCheckOutMVPView;

    private String ordercode = generateOrderCode();
    private String amount = "10000";
    private String fee = "0";
    int environment = 0;//developer default
    private String merchantName = "Nguyễn Văn Vĩnh Nguyên";
    private String merchantCode = "MOMOC2IC20220510";
    private String merchantNameLabel = "Nguyen Van Vinh Nguyen";
    private String description = "Thanh toán đơn hàng Seafood";

    public CheckOutPresenter(Context context, CheckOutMVPView mCheckOutMVPView, int contextMM, Activity currentActivity) {
        this.context = context;
        this.mCheckOutMVPView = mCheckOutMVPView;
        this.contextMM = contextMM;
        this.currentActivity = currentActivity;
    }

    public void getDataAddressPersonal() {
        if (Constant.mDressPersonal == null){
            List<DressPersonal> dressPersonalList = DressPersonalDatabase.getInstance(context).dressPersonalDAO().getListDressPersonal(Constant.CUSTOMER_ID);
            if (dressPersonalList.size() > 0){
                mCheckOutMVPView.getDataAddressPersonalSuccess(dressPersonalList.get(0));
            }else {
                mCheckOutMVPView.getDataAddressPersonalError();
            }
        }else {
            mCheckOutMVPView.getDataAddressPersonalSuccess(Constant.mDressPersonal);
        }
    }

    public void checkCoupon(String coupon_name_code) {
        mCompositeDisposable.add(mRepositoryAPI.checkCoupon(coupon_name_code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                        couponAPI -> {
                           if (couponAPI.getStatus_code() == 200){
                               mCheckOutMVPView.checkCouponSuccess(couponAPI.getData().get(0));
                           } else if (couponAPI.getStatus_code() == 404){
                               mCheckOutMVPView.checkCouponError();
                           }
                }, throwable -> {
                        Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
        }));
    }

    public void calculatingTotalProgress(List<Cart> mCartList, DressPersonal mDressPersonal, Coupon mCoupon) {
        if (mDressPersonal != null) {
            mCompositeDisposable.add(mRepositoryAPI.getDeliveringFee(mDressPersonal.getCity_name(), mDressPersonal.getProvince_name(), mDressPersonal.getWard_name())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(deliveringFeeAPI -> {
                        Double totalPrice = 0.0;
                        for (int i = 0 ;i < mCartList.size() ; i++){
                            totalPrice = totalPrice + (mCartList.get(i).getProduct_price() * mCartList.get(i).getProduct_quantity());
                        }
                        Double deliveringFee = deliveringFeeAPI.getData();
                        Double couponPriceSale = 0.0;
                        if (mCoupon !=null){
                            if (mCoupon.getCoupon_condition() == 1){
                                couponPriceSale =  mCoupon.getCoupon_price_sale() / 100 * totalPrice;
                            }else {
                                couponPriceSale = mCoupon.getCoupon_price_sale();
                            }
                        }
                        Double pricePayment = totalPrice + deliveringFee - couponPriceSale;
                        mCheckOutMVPView.calculatingTotalProgressSuccess(totalPrice , deliveringFee,  couponPriceSale , pricePayment);
                    }, throwable -> {
                        Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }));
        }else {
            Double totalPrice = 0.0;
            for (int i = 0 ;i < mCartList.size() ; i++){
                totalPrice = totalPrice + (mCartList.get(i).getProduct_price() * mCartList.get(i).getProduct_quantity());
            }
            Double deliveringFee = 0.0;
            Double couponPriceSale = 0.0;
            if (mCoupon !=null){
                if (mCoupon.getCoupon_condition() == 1){
                    couponPriceSale =  mCoupon.getCoupon_price_sale() / 100 * totalPrice;
                }else {
                    couponPriceSale = mCoupon.getCoupon_price_sale();
                }
            }
            Double pricePayment = totalPrice + deliveringFee - couponPriceSale;
            mCheckOutMVPView.calculatingTotalProgressSuccess(totalPrice , deliveringFee,  couponPriceSale , pricePayment);
        }

    }

    public void putOrder(Double mTotalPrice, Double mDeliveringFee, Double mCouponPriceSale, String payment_method, List<Cart> mCartList, Coupon mCoupon, DressPersonal mDressPersonal) {
        Payment payment = new Payment();
        Shipping shipping =  new Shipping();

        if (payment_method.equals("Chọn phương thức thanh toán")) {
            mCheckOutMVPView.putOrderError(1);
            return;
        } else if (payment_method.equals("Thanh Toán Khi Nhận Hàng")) {
            payment.setPayment_method(4);
            payment.setPayment_status(0);

        } else if (payment_method.equals("Thanh Toán Bằng Momo")) {
            payment.setPayment_method(1);
            payment.setPayment_status(1);
            /* Note */
        }

        if (mDressPersonal != null) {
            shipping.setShipping_name( mDressPersonal.getShipping_name());
            shipping.setShipping_email( mDressPersonal.getShipping_email());
            shipping.setShipping_phone(  mDressPersonal.getShipping_phone());
            shipping.setShipping_address( mDressPersonal.getHome_number() + ", " + mDressPersonal.getWard_name() + ", " + mDressPersonal.getProvince_name() + ", " + mDressPersonal.getCity_name());
            shipping.setShipping_notes( mDressPersonal.getShipping_name());
            shipping.setShipping_special_requirements( 1);
            shipping.setShipping_receipt( 0);
        } else {
            mCheckOutMVPView.putOrderError(2);
            return;
        }

        if (mDressPersonal != null && !payment_method.equals("Chọn phương thức thanh toán")){
            RequestBody paymentBody = RequestBody.create(MediaType.parse("application/json"), new Gson().toJson(payment));
            RequestBody shippingBody = RequestBody.create(MediaType.parse("application/json"), new Gson().toJson(shipping));
            RequestBody cartBody = RequestBody.create(MediaType.parse("application/json"), new Gson().toJson(mCartList));
            RequestBody couponBody = RequestBody.create(MediaType.parse("application/json"), new Gson().toJson(mCoupon));

            if (payment.getPayment_method() == 4){
                directPayment(mTotalPrice,mDeliveringFee,paymentBody,shippingBody,cartBody,couponBody);
            }else if(payment.getPayment_method() == 1){
                momoPayment(mTotalPrice,mDeliveringFee,mCouponPriceSale,paymentBody,shippingBody,cartBody,couponBody);
            }

        }

    }


    public void resetData(List<Cart> mCartList, Order order) {
        if (Integer.valueOf(mCartList.get(0).getCart_id()) == null){
        }else {
            for (int i = 0 ; i < mCartList.size() ; i++){
                CartDatabase.getInstance(context).cartDAO().DeleteCart(mCartList.get(i));
            }
        }
        mCheckOutMVPView.resetDataSuccess(order);
    }

    private void directPayment(Double mTotalPrice, Double mDeliveringFee, RequestBody paymentBody, RequestBody shippingBody, RequestBody cartBody, RequestBody couponBody) {
        this.ordercode = generateOrderCode();
        mCompositeDisposable.add(mRepositoryAPI.putOrder(ordercode , mTotalPrice ,mDeliveringFee, paymentBody,shippingBody,cartBody,couponBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(orderAPI -> {
                    if (orderAPI.getStatus_code() == 200){
                        mCheckOutMVPView.putOrderSuccess(orderAPI.getData().get(0));
                    }
                }, throwable -> {
                    Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
        }));
    }

    public static String generateOrderCode() {
        int min = 0001;
        int max = 9999;
        Random random = new Random();
        return "ANDROID"+random.nextInt(max - min + 1) + min;
    }


    private void momoPayment(Double mTotalPrice, Double mDeliveringFee, Double mCouponPriceSale, RequestBody paymentBody, RequestBody shippingBody, RequestBody cartBody, RequestBody couponBody) {
        this.ordercode = generateOrderCode();
        AppMoMoLib.getInstance().setAction(AppMoMoLib.ACTION.PAYMENT);
        AppMoMoLib.getInstance().setActionType(AppMoMoLib.ACTION_TYPE.GET_TOKEN);
//        if (edAmount.getText().toString() != null && edAmount.getText().toString().trim().length() != 0)
//            amount = edAmount.getText().toString().trim();
        Map<String, Object> eventValue = new HashMap<>();
        //client Required
        eventValue.put("merchantname", merchantName); //Tên đối tác. được đăng ký tại https://business.momo.vn. VD: Google, Apple, Tiki , CGV Cinemas
        eventValue.put("merchantcode", merchantCode); //Mã đối tác, được cung cấp bởi MoMo tại https://business.momo.vn
        eventValue.put("amount", mTotalPrice + mDeliveringFee - mCouponPriceSale); //Kiểu integer
        eventValue.put("orderId",ordercode); //uniqueue id cho Bill order, giá trị duy nhất cho mỗi đơn hàng
        eventValue.put("orderLabel", "Mã đơn hàng"); //gán nhãn

        //client Optional - bill info
        eventValue.put("merchantnamelabel", "Doanh nghiệp Seafood");//gán nhãn
        eventValue.put("fee", fee); //Kiểu integer
        eventValue.put("description", description); //mô tả đơn hàng - short description

        //client extra data
        eventValue.put("requestId",  merchantCode+"merchant_billId_"+System.currentTimeMillis());
        eventValue.put("partnerCode", merchantCode);
        //Example extra data
        JSONObject objExtraData = new JSONObject();
        try {
            objExtraData.put("site_code", "008");
            objExtraData.put("site_name", "CGV Cresent Mall");
            objExtraData.put("screen_code", 0);
            objExtraData.put("screen_name", "Special");
            objExtraData.put("movie_name", "Kẻ Trộm Mặt Trăng 3");
            objExtraData.put("movie_format", "2D");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        eventValue.put("extraData", objExtraData.toString());
        eventValue.put("extra", "");
        AppMoMoLib.getInstance().requestMoMoCallBack(currentActivity, eventValue);
    }


    public void putOrderMomo(Double mTotalPrice, Double mDeliveringFee, Double mCouponPriceSale, List<Cart> mCartList, Coupon mCoupon, DressPersonal mDressPersonal) {
        Payment payment = new Payment();
        payment.setPayment_method(1);
        payment.setPayment_status(1);
        Shipping shipping =  new Shipping();
        shipping.setShipping_name( mDressPersonal.getShipping_name());
        shipping.setShipping_email( mDressPersonal.getShipping_email());
        shipping.setShipping_phone(  mDressPersonal.getShipping_phone());
        shipping.setShipping_address( mDressPersonal.getHome_number() + ", " + mDressPersonal.getWard_name() + ", " + mDressPersonal.getProvince_name() + ", " + mDressPersonal.getCity_name());
        shipping.setShipping_notes( mDressPersonal.getShipping_name());
        shipping.setShipping_special_requirements( 1);
        shipping.setShipping_receipt( 0);
        RequestBody paymentBody = RequestBody.create(MediaType.parse("application/json"), new Gson().toJson(payment));
        RequestBody shippingBody = RequestBody.create(MediaType.parse("application/json"), new Gson().toJson(shipping));
        RequestBody cartBody = RequestBody.create(MediaType.parse("application/json"), new Gson().toJson(mCartList));
        RequestBody couponBody = RequestBody.create(MediaType.parse("application/json"), new Gson().toJson(mCoupon));
        mCompositeDisposable.add(mRepositoryAPI.putOrder(ordercode , mTotalPrice ,mDeliveringFee, paymentBody,shippingBody,cartBody,couponBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(orderAPI -> {
                    if (orderAPI.getStatus_code() == 200){
                        mCheckOutMVPView.putOrderSuccess(orderAPI.getData().get(0));
                    }
                }, throwable -> {
                    Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
        }));
    }
}
