package com.nhanlovecode.doancuoiky.Views.Order.OrderDeliveringTab;

import android.content.Context;
import android.widget.Toast;

import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseLocal.SharedPreferences.MySharedPreferencesManager;
import com.nhanlovecode.doancuoiky.DatabaseServer.RepositoryAPI;
import com.nhanlovecode.doancuoiky.DatabaseServer.RetrofitClient;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class OrderDeliveringTabPresenter {
    CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    RepositoryAPI mRepositoryAPI = RetrofitClient.getInstance(Constant.BASE_URL).create(RepositoryAPI.class);

    Context context;
    OrderDeliveringTabMVPView mOrderProTabMVPView;

    public OrderDeliveringTabPresenter(Context context, OrderDeliveringTabMVPView mOrderProTabMVPView) {
        this.context = context;
        this.mOrderProTabMVPView = mOrderProTabMVPView;
    }


    public void getDataOrderDelivering() {
        int customer_id = MySharedPreferencesManager.getCustomer(Constant.PREF_KEY_CUSTOMER).getCustomer_id();
        int order_status = Constant.ORDER_CODE_DELIVERING;
        mCompositeDisposable.add(mRepositoryAPI.getOrder(customer_id ,order_status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        orderAPI -> {
                            if (orderAPI.getStatus_code() == 200){
                                mOrderProTabMVPView.getDataOrderDeliveringSuccess(orderAPI.getData());
                            }else {
                                mOrderProTabMVPView.getDataOrderDeliveringError();
                            }
                        },
                        throwable -> {
                            Toast.makeText(context,throwable.getMessage() ,Toast.LENGTH_LONG).show();
                        }
                ));
    }

    public void searchDataOrderDelivering(String order_code) {
        int customer_id = MySharedPreferencesManager.getCustomer(Constant.PREF_KEY_CUSTOMER).getCustomer_id();
        int order_status = Constant.ORDER_CODE_DELIVERING;
        mCompositeDisposable.add(mRepositoryAPI.searchOrder(customer_id ,order_status , order_code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        orderAPI -> {
                            if (orderAPI.getStatus_code() == 200){
                                mOrderProTabMVPView.searchDataOrderDeliveringSuccess(orderAPI.getData());
                            }else {
                                mOrderProTabMVPView.searchDataOrderDeliveringError();
                            }
                        },
                        throwable -> {
                            Toast.makeText(context,throwable.getMessage() ,Toast.LENGTH_LONG).show();
                        }
                ));
    }

    public void orderRecieve(String order_code) {
        mCompositeDisposable.add(mRepositoryAPI.receiveOrder(order_code).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(orderAPI -> {
                    mOrderProTabMVPView.getReceiveOrderSuccess();
                }, throwable -> {
                    Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }));
    }
}
