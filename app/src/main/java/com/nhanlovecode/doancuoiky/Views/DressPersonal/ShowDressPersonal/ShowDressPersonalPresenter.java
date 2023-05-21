package com.nhanlovecode.doancuoiky.Views.DressPersonal.ShowDressPersonal;

import android.content.Context;
import android.widget.Toolbar;

import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseLocal.RoomDatabase.DressPersonalDatabase;
import com.nhanlovecode.doancuoiky.DatabaseLocal.SharedPreferences.MySharedPreferencesManager;
import com.nhanlovecode.doancuoiky.Models.DressPersonal;

import java.util.List;

public class ShowDressPersonalPresenter {
    Context context;
    ShowDressPersonalMVPView mShowDressPersonalMVPView;

    public ShowDressPersonalPresenter(Context context, ShowDressPersonalMVPView mShowDressPersonalMVPView) {
        this.context = context;
        this.mShowDressPersonalMVPView = mShowDressPersonalMVPView;
    }

    public void getDataDressPersonal() {
       List<DressPersonal> dressPersonalList = DressPersonalDatabase.getInstance(context).dressPersonalDAO().getListDressPersonal(Constant.CUSTOMER_ID);
       if (dressPersonalList.size() > 0){
           mShowDressPersonalMVPView.getDataDressPersonalSuccess(dressPersonalList);
       }else {
           mShowDressPersonalMVPView.getDataDressPersonalError();
       }
    }

    public void updateDataDressPersonal(DressPersonal dressPersonal) {
        if (dressPersonal.getChecked()){
            mShowDressPersonalMVPView.updateDataDressPersonalError();
        }else {
            List<DressPersonal> listDressPersonal = DressPersonalDatabase.getInstance(context).dressPersonalDAO().getListDressPersonal(MySharedPreferencesManager.getCustomer(Constant.PREF_KEY_CUSTOMER).getCustomer_id());
            for (DressPersonal personal : listDressPersonal) {
                if (personal.getChecked()) {
                    personal.setChecked(false);
                    DressPersonalDatabase.getInstance(context).dressPersonalDAO().UpdateDressPersonal(personal);
                }
            }
            dressPersonal.setChecked(!dressPersonal.getChecked());
            DressPersonalDatabase.getInstance(context).dressPersonalDAO().UpdateDressPersonal(dressPersonal);
            Constant.mDressPersonal = dressPersonal;
            mShowDressPersonalMVPView.updateDataDressPersonalSuccess();
        }
    }

    public void loadDataDressPersonal() {
        List<DressPersonal> dressPersonalList = DressPersonalDatabase.getInstance(context).dressPersonalDAO().getListDressPersonal(Constant.CUSTOMER_ID);
        if (dressPersonalList.size() > 0){
            mShowDressPersonalMVPView.loadDataDressPersonalSuccess(dressPersonalList);
        }else {
            mShowDressPersonalMVPView.loadDataDressPersonalError();
        }
    }

    public void deleteDataDressPersonal(DressPersonal dressPersonal) {
        DressPersonalDatabase.getInstance(context).dressPersonalDAO().DeleteDressPersonal(dressPersonal);
        mShowDressPersonalMVPView.deleteDataDressPersonalSuccess(dressPersonal);
    }
}
