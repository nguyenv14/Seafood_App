package com.nhanlovecode.doancuoiky.Views.Personal.Evaluate;

import android.content.Context;

public class EvaluatePresenter {
    Context context;
    EvaluateMVPView evaluateMVPView;

    public EvaluatePresenter(Context context, EvaluateMVPView evaluateMVPView) {
        this.context = context;
        this.evaluateMVPView = evaluateMVPView;
    }

}
