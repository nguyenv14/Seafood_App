package com.nhanlovecode.doancuoiky.Views.Favorite;

import com.nhanlovecode.doancuoiky.Models.Favorite;

import java.util.List;

public interface FavoriteMVPView {
    void getFavoriteListSuccess(List<Favorite> favoriteList);
    void getFavoriteListNull();

}
