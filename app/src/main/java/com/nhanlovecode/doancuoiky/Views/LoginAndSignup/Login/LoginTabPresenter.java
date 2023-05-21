package com.nhanlovecode.doancuoiky.Views.LoginAndSignup.Login;

import android.content.Context;
import android.widget.Toast;

import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseLocal.SharedPreferences.MySharedPreferencesManager;
import com.nhanlovecode.doancuoiky.DatabaseServer.RepositoryAPI;
import com.nhanlovecode.doancuoiky.DatabaseServer.RetrofitClient;
import com.nhanlovecode.doancuoiky.Models.Customer;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginTabPresenter {

    CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    RepositoryAPI mRepositoryAPI = RetrofitClient.getInstance(Constant.BASE_URL).create(RepositoryAPI.class);

    Context context;
    LoginTabMVPView mLoginTabMVPView;

    public LoginTabPresenter(Context context, LoginTabMVPView mLoginTabMVPView) {
        this.context = context;
        this.mLoginTabMVPView = mLoginTabMVPView;
    }


    public void checkLogin(String nameOrEmail, String passWord) {
        if(nameOrEmail.isEmpty() || passWord.isEmpty()){
            mLoginTabMVPView.isValidator();
        }else{
            mCompositeDisposable.add(mRepositoryAPI.checkLogin(nameOrEmail , passWord)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            loginAPI -> {
                                if(loginAPI.getStatus_code() == 200){
//                                    Toast.makeText(context, loginAPI.getData().getCustomer_name(), Toast.LENGTH_SHORT).show();
                                    saveSessionLogin(loginAPI.getData());
                                    mLoginTabMVPView.isLoginSuccess();

                                }else if (loginAPI.getStatus_code() == 404){
                                    mLoginTabMVPView.isLoginError();

                                }
                            },
                            throwable -> {
                                Toast.makeText(context,throwable.getMessage(),Toast.LENGTH_LONG).show();
                            }
                    ));
        }
    }

    public void sendCodeChangePass(String customer_email){
        mCompositeDisposable.add(mRepositoryAPI.sendCodeChangePass(customer_email, 1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(customerNewAPI -> {
                    if(customerNewAPI.getStatus_code() == 200){
                        mLoginTabMVPView.sendCodeChangePassSuccess(customerNewAPI.getData().get(0));
                    }else{
                        Toast.makeText(context, "Email không được đăng ký", Toast.LENGTH_SHORT).show();
                    }
                }, throwable -> {
                    Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }));
    }

    private void saveSessionLogin(Customer customer) {
        MySharedPreferencesManager.putCustomer(Constant.PREF_KEY_CUSTOMER , customer);
    }
}
