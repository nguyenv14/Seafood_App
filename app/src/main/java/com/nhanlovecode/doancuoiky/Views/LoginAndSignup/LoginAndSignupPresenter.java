package com.nhanlovecode.doancuoiky.Views.LoginAndSignup;

import android.content.Context;

public class LoginAndSignupPresenter {
    LoginAndSignupMVPView mLoginAndSignupMVPView;
    Context context;

    public LoginAndSignupPresenter(LoginAndSignupMVPView mLoginAndSignupMVPView, Context context) {
        this.mLoginAndSignupMVPView = mLoginAndSignupMVPView;
        this.context = context;
    }
}
