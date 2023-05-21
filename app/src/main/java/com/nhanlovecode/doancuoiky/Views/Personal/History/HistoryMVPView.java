package com.nhanlovecode.doancuoiky.Views.Personal.History;

import com.nhanlovecode.doancuoiky.Models.History;

import java.util.List;

public interface HistoryMVPView {
    void getListDataHistorySuccess(List<History> historyList);
    void getListDataHistoryNull();
}
