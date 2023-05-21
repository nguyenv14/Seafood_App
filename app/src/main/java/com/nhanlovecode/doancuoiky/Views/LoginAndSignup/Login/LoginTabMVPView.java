package com.nhanlovecode.doancuoiky.Views.LoginAndSignup.Login;

import com.nhanlovecode.doancuoiky.Models.CustomerNew;

public interface LoginTabMVPView {
    void isValidator();

    void isLoginSuccess();

    void isLoginError();

    void sendCodeChangePassSuccess(CustomerNew customerNew);
}
