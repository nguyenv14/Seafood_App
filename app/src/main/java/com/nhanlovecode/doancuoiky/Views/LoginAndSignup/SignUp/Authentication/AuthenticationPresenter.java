package com.nhanlovecode.doancuoiky.Views.LoginAndSignup.SignUp.Authentication;

import android.content.Context;
import android.widget.Toast;

import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseServer.RepositoryAPI;
import com.nhanlovecode.doancuoiky.DatabaseServer.RetrofitClient;
import com.nhanlovecode.doancuoiky.Models.Customer;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AuthenticationPresenter {
    Context context;
    AuthenticationMVPViews authenticationMVPViews;

    RepositoryAPI repositoryAPI = RetrofitClient.getInstance(Constant.BASE_URL).create(RepositoryAPI.class);
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public AuthenticationPresenter(Context context, AuthenticationMVPViews authenticationMVPViews) {
        this.context = context;
        this.authenticationMVPViews = authenticationMVPViews;
    }

    public void signUp(Customer customer){
        compositeDisposable.add(repositoryAPI.signUpCustomer(customer.getCustomer_name(), customer.getCustomer_phone(), customer.getCustomer_email(), customer.getCustomer_password())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                   customerAPI -> {
                       if(customerAPI.getStatus_code() == 200){
                           authenticationMVPViews.getSignUpSuccess();
                       }
                   }, throwable -> {
                       Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                   }
                ));
    }

    public void sendCode(String customer_email, String customer_name, int status){
        if(status == 1){
            compositeDisposable.add(repositoryAPI.sendCodeChangePass(customer_email, status).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(customerNewAPI -> {
                        authenticationMVPViews.getReSendCodeSuccess(customerNewAPI.getData().get(0));
                    }, throwable -> {
                        Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }));
        }else{
            compositeDisposable.add(repositoryAPI.sendCodeCustomerNew(customer_name, customer_email, status).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(customerNewAPI -> {
                        authenticationMVPViews.getReSendCodeSuccess(customerNewAPI.getData().get(0));
                    }, throwable -> {
                        Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }));
        }
    }
}
