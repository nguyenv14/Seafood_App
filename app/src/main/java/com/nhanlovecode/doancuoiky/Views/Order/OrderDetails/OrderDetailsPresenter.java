package com.nhanlovecode.doancuoiky.Views.Order.OrderDetails;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseServer.RepositoryAPI;
import com.nhanlovecode.doancuoiky.DatabaseServer.RetrofitClient;
import com.nhanlovecode.doancuoiky.Models.Order;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class OrderDetailsPresenter {
    Context context;
    OrderDetailsMVPView mOrderDetailsMVPView;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    RepositoryAPI repositoryAPI = RetrofitClient.getInstance(Constant.BASE_URL).create(RepositoryAPI.class);
    public OrderDetailsPresenter(Context context, OrderDetailsMVPView mOrderDetailsMVPView) {
        this.context = context;
        this.mOrderDetailsMVPView = mOrderDetailsMVPView;
    }

    public void getDataOrder(Intent intent) {
        String strOrder = intent.getStringExtra("strOrder");
        if (strOrder != null){
            Gson gson = new Gson();
            Order order = gson.fromJson(strOrder , Order.class);
            mOrderDetailsMVPView.getDataOrderSuccess(order);
        }else {
            mOrderDetailsMVPView.getDataOrderErorr();
        }
    }
    public void getOrderDetails(String order_code){
        compositeDisposable.add(repositoryAPI.getOrderDetails(order_code).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(orderAPI -> {
                    mOrderDetailsMVPView.getDataOrderSuccess(orderAPI.getData().get(0));
                }, throwable -> {
                    Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }));
    }
    public void getEvaluate(String order_code){
//        Toast.makeText(context, order_code, Toast.LENGTH_SHORT).show();
        compositeDisposable.add(repositoryAPI.getEvaluateOrder(order_code).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(commentAPI -> {
                    if(commentAPI.getStatus_code() == 200){
                        mOrderDetailsMVPView.getEvaluateOrderSuccess(commentAPI.getData().get(0));
                    }else if(commentAPI.getStatus_code() == 404){
                        mOrderDetailsMVPView.getEvaluateOrderNull();
                    }
                }, throwable -> {
                    Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }));
    }
    public void receiveOrder(String order_code){
        compositeDisposable.add(repositoryAPI.receiveOrder(order_code).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(orderAPI -> {
                    mOrderDetailsMVPView.getDataOrderSuccess(orderAPI.getData().get(0));
                }, throwable -> {
                    Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }));
    }

    public void cancelOrder(String order_code){
        compositeDisposable.add(repositoryAPI.cancelOrder(order_code).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(orderAPI -> {
                    mOrderDetailsMVPView.getDataOrderSuccess(orderAPI.getData().get(0));
                }, throwable -> {
                    Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }));
    }
}
