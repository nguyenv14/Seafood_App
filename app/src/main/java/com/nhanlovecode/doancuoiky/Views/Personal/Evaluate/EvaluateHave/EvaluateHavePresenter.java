package com.nhanlovecode.doancuoiky.Views.Personal.Evaluate.EvaluateHave;

import android.content.Context;
import android.widget.Toast;

import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseServer.RepositoryAPI;
import com.nhanlovecode.doancuoiky.DatabaseServer.RetrofitClient;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class EvaluateHavePresenter {

    Context context;
    EvaluateHaveMVPViews mEvaluateHaveMVPViews;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    RepositoryAPI repositoryAPI = RetrofitClient.getInstance(Constant.BASE_URL).create(RepositoryAPI.class);

    public EvaluateHavePresenter(Context context, EvaluateHaveMVPViews mEvaluateHaveMVPViews) {
        this.context = context;
        this.mEvaluateHaveMVPViews = mEvaluateHaveMVPViews;
    }

    public void getEvaluateHave(int customer_id){
        compositeDisposable.add(repositoryAPI.getEvaluateOrder(customer_id, Constant.ORDER_CODE_COMPLETED).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(orderAPI -> {
                    if(orderAPI.getStatus_code() == 200){
                        mEvaluateHaveMVPViews.getEvaluateHaveSuccess(orderAPI.getData());
                    }else if(orderAPI.getStatus_code() == 404){
                        mEvaluateHaveMVPViews.getEvaluateHaveError();
                    }
                }, throwable -> {
                    Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }));
    }
}
