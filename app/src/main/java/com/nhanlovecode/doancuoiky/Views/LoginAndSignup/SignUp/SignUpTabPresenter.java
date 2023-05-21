package com.nhanlovecode.doancuoiky.Views.LoginAndSignup.SignUp;

import android.content.Context;
import android.widget.Toast;

import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseServer.RepositoryAPI;
import com.nhanlovecode.doancuoiky.DatabaseServer.RetrofitClient;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SignUpTabPresenter {
    Context context;
    SignUpTabMVPView signUpTabMVPView;

    RepositoryAPI repositoryAPI = RetrofitClient.getInstance(Constant.BASE_URL).create(RepositoryAPI.class);

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public SignUpTabPresenter(Context context, SignUpTabMVPView signUpTabMVPView) {
        this.context = context;
        this.signUpTabMVPView = signUpTabMVPView;
    }

    public void sendCodeEmailCustomer(String email, String customer_name){

        compositeDisposable.add(repositoryAPI.sendCodeCustomerNew(customer_name, email, 0).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                        customerNewAPI -> {
                            if(customerNewAPI.getStatus_code() == 200){
                                signUpTabMVPView.getSendCodeCustomerSuccess(customerNewAPI.getData().get(0));
                            }else if(customerNewAPI.getStatus_code() == 404){
                                Toast.makeText(context, "Email đã tồn tại. Vui lòng chọn email mới!", Toast.LENGTH_SHORT).show();
                            }
                        }, throwable -> {
                            Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                ));
    }


}
