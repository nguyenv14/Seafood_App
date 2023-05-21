package com.nhanlovecode.doancuoiky.EventClickListener;

import com.nhanlovecode.doancuoiky.Models.Favorite;

public interface ItemFavoriteListener {
    public void onClickItemListenner(Favorite favorite);


    public void clickUnFavorite(Favorite favorite);

    void clickToSimilar(Favorite favorite);
}
