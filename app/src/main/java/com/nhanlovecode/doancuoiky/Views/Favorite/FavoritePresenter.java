package com.nhanlovecode.doancuoiky.Views.Favorite;

import android.content.Context;
import android.widget.Toast;

import com.nhanlovecode.doancuoiky.DatabaseLocal.RoomDatabase.FavoriteDatabase;
import com.nhanlovecode.doancuoiky.Models.Favorite;

import java.util.List;

public class FavoritePresenter {
    Context context;
    FavoriteMVPView favoriteMVPView;

    public FavoritePresenter(Context context, FavoriteMVPView favoriteMVPView) {
        this.context = context;
        this.favoriteMVPView = favoriteMVPView;
    }

    public void getFavoriteList(int customer_id){
        List<Favorite> favoriteList = FavoriteDatabase.getInstance(context).favoriteDAO().getListFavorite(customer_id);
        if(favoriteList.isEmpty()){
            favoriteMVPView.getFavoriteListNull();
        }else{
            favoriteMVPView.getFavoriteListSuccess(favoriteList);
        }
    }

    public void unFavorite(Favorite favorite){
        FavoriteDatabase.getInstance(context).favoriteDAO().deleteFavorite(favorite);
        Toast.makeText(context, "Đã xóa khỏi danh sách yêu thích", Toast.LENGTH_SHORT).show();
    }

}
