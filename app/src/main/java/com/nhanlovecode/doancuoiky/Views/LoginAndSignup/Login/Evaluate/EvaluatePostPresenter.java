package com.nhanlovecode.doancuoiky.Views.LoginAndSignup.Login.Evaluate;

import android.content.Context;
import android.widget.Toast;

import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseServer.RepositoryAPI;
import com.nhanlovecode.doancuoiky.DatabaseServer.RetrofitClient;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class EvaluatePostPresenter {
    Context context;
    EvaluatePostMVPView evaluatePostMVPView;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    RepositoryAPI repositoryAPI = RetrofitClient.getInstance(Constant.BASE_URL).create(RepositoryAPI.class);

    public EvaluatePostPresenter(Context context, EvaluatePostMVPView evaluatePostMVPView) {
        this.context = context;
        this.evaluatePostMVPView = evaluatePostMVPView;
    }

    public void evaluatePost(String order_code, String comment_title, String comment_content, int evaluate_star){
        compositeDisposable.add(repositoryAPI.insertEvaluateOrder(order_code, comment_title, comment_content, evaluate_star)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(commentAPI -> {
                    if(commentAPI.getStatus_code() == 200){
                        evaluatePostMVPView.postEvaluateSuccess();
                    }
                }, throwable -> {
                    Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }));
    }


}
