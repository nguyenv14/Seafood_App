package com.nhanlovecode.doancuoiky.Views.Welcome;

import android.content.Context;

public class WelcomPresenter {
    WelcomMVPView mWelcomMVPView;
    Context context;

    public WelcomPresenter(WelcomMVPView mWelcomMVPView, Context context) {
        this.mWelcomMVPView = mWelcomMVPView;
        this.context = context;
    }
}
