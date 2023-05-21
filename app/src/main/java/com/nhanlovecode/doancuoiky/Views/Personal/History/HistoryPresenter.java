package com.nhanlovecode.doancuoiky.Views.Personal.History;

import android.content.Context;

import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseLocal.RoomDatabase.HistoryDatabase;
import com.nhanlovecode.doancuoiky.Models.History;

import java.util.List;

public class HistoryPresenter {

    Context context;
    HistoryMVPView historyMVPView;

    public HistoryPresenter(Context context, HistoryMVPView historyMVPView) {
        this.context = context;
        this.historyMVPView = historyMVPView;
    }

    public void getListDataHistory(){
        List<History> historyList = HistoryDatabase.getInstance(context).historyDAO().getHistoryProductList(Constant.CUSTOMER_ID);
        if(historyList.isEmpty()){
            historyMVPView.getListDataHistoryNull();
        }else{
            historyMVPView.getListDataHistorySuccess(historyList);
        }
    }
}
