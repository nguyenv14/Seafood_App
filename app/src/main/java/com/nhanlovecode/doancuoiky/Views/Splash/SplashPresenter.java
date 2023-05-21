package com.nhanlovecode.doancuoiky.Views.Splash;

import android.content.Context;

import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseLocal.SharedPreferences.MySharedPreferencesManager;
import com.nhanlovecode.doancuoiky.Models.Customer;

public class SplashPresenter {
    private SplashMVPView mSplashMVPView;
    private Context context;

    public SplashPresenter(SplashMVPView mSplashMVPView, Context context) {
        this.mSplashMVPView = mSplashMVPView;
        this.context = context;
    }


    public void checkFirstInstallApp(){
        if(MySharedPreferencesManager.getFirstInstallApp(Constant.PREF_KEY_FIRST_INSTALL)){
            mSplashMVPView.noFirstInstallApp();
        }else {
            MySharedPreferencesManager.putFirstInstallApp(Constant.PREF_KEY_FIRST_INSTALL , true);
            mSplashMVPView.isFirstInstallApp();
        }
    }

    public void checkSessionLogin(){
        Customer customer = MySharedPreferencesManager.getCustomer(Constant.PREF_KEY_CUSTOMER);
        if(customer != null){
            mSplashMVPView.isLogged();
        }else {
            mSplashMVPView.notLogged();
        }

    }
}
