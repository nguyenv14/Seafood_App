package com.nhanlovecode.doancuoiky.Views.Main;

import android.content.Context;

public class MainPresenter {
    MainMVPView mMainMVPView;
    Context context;

    public MainPresenter(MainMVPView mMainMVPView, Context context) {
        this.mMainMVPView = mMainMVPView;
        this.context = context;
    }
}
