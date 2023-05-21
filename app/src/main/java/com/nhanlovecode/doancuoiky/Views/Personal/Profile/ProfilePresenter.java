package com.nhanlovecode.doancuoiky.Views.Personal.Profile;

import android.content.Context;
import android.widget.Toast;

import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseLocal.SharedPreferences.MySharedPreferencesManager;
import com.nhanlovecode.doancuoiky.DatabaseServer.RepositoryAPI;
import com.nhanlovecode.doancuoiky.DatabaseServer.RetrofitClient;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProfilePresenter {
    Context context;
    ProfileMVPView profileMVPView;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    RepositoryAPI repositoryAPI = RetrofitClient.getInstance(Constant.BASE_URL).create(RepositoryAPI.class);

    public ProfilePresenter(Context context, ProfileMVPView profileMVPView) {
        this.context = context;
        this.profileMVPView = profileMVPView;
    }

//    public void updateProfileType(int type, String updatetext){
//        compositeDisposable.add(repositoryAPI.updateUserType(type, updatetext, Constant.CUSTOMER_ID)
//                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(customer -> {
//                    profileMVPView.updateUserTypeSuccess(customer, type);
//                }, throwable -> {
//                    Toast.makeText(context, "aaa" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
//                }));
//    }

    public void updateName(String name, int customer_id){
        Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
        compositeDisposable.add(repositoryAPI.updateUserName(name, customer_id)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(customerAPI -> {
                    MySharedPreferencesManager.putCustomer(Constant.PREF_KEY_CUSTOMER, customerAPI.getData().get(0));
//                    Toast.makeText(context, customer.getCustomer_name(), Toast.LENGTH_SHORT).show();
                    profileMVPView.updateUserTypeSuccess(customerAPI.getData().get(0));
                }, throwable -> {
                    Toast.makeText(context, "aaa" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }));
    }

    public void updatePhone(Long phone, int customer_id){
        Toast.makeText(context, "a" + phone, Toast.LENGTH_SHORT).show();
        compositeDisposable.add(repositoryAPI.updateUserPhone(phone, customer_id)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(customerAPI -> {
                    MySharedPreferencesManager.putCustomer(Constant.PREF_KEY_CUSTOMER, customerAPI.getData().get(0));
                    Toast.makeText(context, "se" + customerAPI.getData().get(0).getCustomer_phone(), Toast.LENGTH_SHORT).show();
                    profileMVPView.updateUserTypeSuccess(customerAPI.getData().get(0));
                }, throwable -> {
                    Toast.makeText(context, "aaa" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }));
    }
    public void updateEmail(String email, int customer_id){
        compositeDisposable.add(repositoryAPI.updateUserEmail(email, customer_id)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(customerAPI -> {
                    MySharedPreferencesManager.putCustomer(Constant.PREF_KEY_CUSTOMER, customerAPI.getData().get(0));
                    profileMVPView.updateUserTypeSuccess(customerAPI.getData().get(0));

                }, throwable -> {
                    Toast.makeText(context, "aaa" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }));
    }
}

