package com.nhanlovecode.doancuoiky.Views.Order.OrderCancelledTab;

import android.content.Context;
import android.widget.Toast;

import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseLocal.SharedPreferences.MySharedPreferencesManager;
import com.nhanlovecode.doancuoiky.DatabaseServer.RepositoryAPI;
import com.nhanlovecode.doancuoiky.DatabaseServer.RetrofitClient;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class OrderCancelledTabPresenter {
    CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    RepositoryAPI mRepositoryAPI = RetrofitClient.getInstance(Constant.BASE_URL).create(RepositoryAPI.class);

    Context context;
    OrderCancelledTabMVPView mOrderProTabMVPView;

    public OrderCancelledTabPresenter(Context context, OrderCancelledTabMVPView mOrderProTabMVPView) {
        this.context = context;
        this.mOrderProTabMVPView = mOrderProTabMVPView;
    }


    public void getDataOrderCancelled() {
        int customer_id = MySharedPreferencesManager.getCustomer(Constant.PREF_KEY_CUSTOMER).getCustomer_id();
        int order_status = Constant.ORDER_CODE_CANCELLED_BY_ADMIN;
        mCompositeDisposable.add(mRepositoryAPI.getOrder(customer_id ,order_status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        orderAPI -> {
                            if (orderAPI.getStatus_code() == 200){
                                mOrderProTabMVPView.getDataOrderCancelledSuccess(orderAPI.getData());
                            }else {
                                mOrderProTabMVPView.getDataOrderCancelledError();
                            }
                        },
                        throwable -> {
                            Toast.makeText(context,throwable.getMessage() ,Toast.LENGTH_LONG).show();
                        }
                ));
    }

    public void searchDataOrderCancelled(String order_code) {

        int customer_id = MySharedPreferencesManager.getCustomer(Constant.PREF_KEY_CUSTOMER).getCustomer_id();
        int order_status = Constant.ORDER_CODE_CANCELLED_BY_ADMIN;
        mCompositeDisposable.add(mRepositoryAPI.searchOrder(customer_id ,order_status , order_code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        orderAPI -> {
                            if (orderAPI.getStatus_code() == 200){
                                mOrderProTabMVPView.searchDataOrderCancelledSuccess(orderAPI.getData());
                            }else {
                                mOrderProTabMVPView.searchDataOrderCancelledError();
                            }
                        },
                        throwable -> {
                            Toast.makeText(context,throwable.getMessage() ,Toast.LENGTH_LONG).show();
                        }
                ));
    }
}
