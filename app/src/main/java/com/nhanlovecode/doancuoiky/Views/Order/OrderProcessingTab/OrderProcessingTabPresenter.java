package com.nhanlovecode.doancuoiky.Views.Order.OrderProcessingTab;

import android.content.Context;
import android.widget.Toast;

import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseLocal.SharedPreferences.MySharedPreferencesManager;
import com.nhanlovecode.doancuoiky.DatabaseServer.RepositoryAPI;
import com.nhanlovecode.doancuoiky.DatabaseServer.RetrofitClient;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class OrderProcessingTabPresenter {
    CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    RepositoryAPI mRepositoryAPI = RetrofitClient.getInstance(Constant.BASE_URL).create(RepositoryAPI.class);

    Context context;
    OrderProcessingTabMVPView mOrderProTabMVPView;

    public OrderProcessingTabPresenter(Context context, OrderProcessingTabMVPView mOrderProTabMVPView) {
        this.context = context;
        this.mOrderProTabMVPView = mOrderProTabMVPView;
    }


    public void getDataOrderProcessing() {
        int customer_id = MySharedPreferencesManager.getCustomer(Constant.PREF_KEY_CUSTOMER).getCustomer_id();
        int order_status = Constant.ORDER_CODE_PROCESSING;
        mCompositeDisposable.add(mRepositoryAPI.getOrder(customer_id ,order_status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        orderAPI -> {
                            if (orderAPI.getStatus_code() == 200){
                                mOrderProTabMVPView.getDataOrderProcessingSuccess(orderAPI.getData());
                            }else {
                                mOrderProTabMVPView.getDataOrderProcessingError();
                            }
                        },
                        throwable -> {
                            Toast.makeText(context,throwable.getMessage() ,Toast.LENGTH_LONG).show();
                        }
                ));
    }

    public void orderCancel(String order_code) {
        mCompositeDisposable.add(mRepositoryAPI.cancelOrder(order_code).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(orderAPI -> {
                    mOrderProTabMVPView.cancelOrderSuccess();
                }, throwable -> {
                    Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }));
    }

    public void searchDataOrderProcessing(String order_code) {
        int customer_id = MySharedPreferencesManager.getCustomer(Constant.PREF_KEY_CUSTOMER).getCustomer_id();
        int order_status = Constant.ORDER_CODE_PROCESSING;
        mCompositeDisposable.add(mRepositoryAPI.searchOrder(customer_id ,order_status , order_code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        orderAPI -> {
                            if (orderAPI.getStatus_code() == 200){
                                mOrderProTabMVPView.searchDataOrderProcessingSuccess(orderAPI.getData());
                            }else {
                                mOrderProTabMVPView.searchDataOrderProcessingError();
                            }
                        },
                        throwable -> {
                            Toast.makeText(context,throwable.getMessage() ,Toast.LENGTH_LONG).show();
                        }
                ));
    }
}
