package com.nhanlovecode.doancuoiky.Views.Personal.Evaluate.EvaluateWait;

import android.content.Context;
import android.widget.Toast;

import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseServer.RepositoryAPI;
import com.nhanlovecode.doancuoiky.DatabaseServer.RetrofitClient;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class EvaluateWaitPresenter {
    Context context;
    EvaluateWaitMVPViews mEvaluateMVPView;
    CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    RepositoryAPI mRepositoryAPI = RetrofitClient.getInstance(Constant.BASE_URL).create(RepositoryAPI.class);
    public EvaluateWaitPresenter(Context context, EvaluateWaitMVPViews mEvaluateMVPView) {
        this.context = context;
        this.mEvaluateMVPView = mEvaluateMVPView;
    }

    public void getEvaluateWait(int customer_id){
        mCompositeDisposable.add(mRepositoryAPI.getEvaluateOrder(customer_id, Constant.ORDER_CODE_COMPLETED_NOT_YET_RATED)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(orderAPI -> {
                    if(orderAPI.getStatus_code() == 200){
                        mEvaluateMVPView.getEvaluateWaitSuccess(orderAPI.getData());
                    }else if(orderAPI.getStatus_code() == 404){
                        mEvaluateMVPView.getEvaluateWaitError();
                    }
                }, throwable -> {
                    Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }));
    }
}
