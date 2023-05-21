package com.nhanlovecode.doancuoiky.Views.LoginAndSignup.SignUp.Authentication;

import com.nhanlovecode.doancuoiky.Models.CustomerNew;

public interface AuthenticationMVPViews {
    public void getSignUpSuccess();

    void getReSendCodeSuccess(CustomerNew customerNew);
}
