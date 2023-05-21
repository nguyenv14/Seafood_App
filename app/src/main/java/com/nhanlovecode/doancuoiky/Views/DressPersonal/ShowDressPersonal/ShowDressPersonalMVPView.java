package com.nhanlovecode.doancuoiky.Views.DressPersonal.ShowDressPersonal;

import com.nhanlovecode.doancuoiky.Models.DressPersonal;

import java.util.List;

public interface ShowDressPersonalMVPView {
    void getDataDressPersonalError();

    void getDataDressPersonalSuccess(List<DressPersonal> dressPersonalList);

    void updateDataDressPersonalSuccess();

    void updateDataDressPersonalError();

    void loadDataDressPersonalSuccess(List<DressPersonal> dressPersonalList);

    void loadDataDressPersonalError();

    void deleteDataDressPersonalSuccess(DressPersonal dressPersonal);
}
