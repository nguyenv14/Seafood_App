package com.nhanlovecode.doancuoiky.Views.Favorite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.nhanlovecode.doancuoiky.Adapters.FavoriteAdapter;
import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseLocal.SharedPreferences.MySharedPreferencesManager;
import com.nhanlovecode.doancuoiky.EventClickListener.ItemFavoriteListener;
import com.nhanlovecode.doancuoiky.Models.Favorite;
import com.nhanlovecode.doancuoiky.Models.Product;
import com.nhanlovecode.doancuoiky.R;
import com.nhanlovecode.doancuoiky.Views.Similar.SimilarActivity;
import com.nhanlovecode.doancuoiky.Views.ProductDetails.ProductDetailsActivity;

import java.io.Serializable;
import java.util.List;

public class FavoritePageFragment extends Fragment implements FavoriteMVPView {
    Context context;
    RecyclerView recyclerviewFavorite;
    LottieAnimationView imgNull;
    FavoritePresenter favoritePresenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        favoritePresenter = new FavoritePresenter(context, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite_page, container, false);

        initUI(view);
        evenClick();
        getData();
        return view;
    }

    private void evenClick() {

    }




    private void getData() {
        favoritePresenter.getFavoriteList(MySharedPreferencesManager.getCustomer(Constant.PREF_KEY_CUSTOMER).getCustomer_id());
    }

    @Override
    public void onResume() {
        super.onResume();
        favoritePresenter.getFavoriteList(MySharedPreferencesManager.getCustomer(Constant.PREF_KEY_CUSTOMER).getCustomer_id());
    }

    private void initUI(View view) {
        recyclerviewFavorite = view.findViewById(R.id.recyclerviewFavorite);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 2);
        recyclerviewFavorite.setLayoutManager(layoutManager);
        recyclerviewFavorite.setHasFixedSize(true);
        imgNull = view.findViewById(R.id.imageNull);
    }

    @Override
    public void getFavoriteListSuccess(List<Favorite> favoriteList) {
        imgNull.setVisibility(View.GONE);
        recyclerviewFavorite.setVisibility(View.VISIBLE);
        FavoriteAdapter favoriteAdapter = new FavoriteAdapter(context, favoriteList, new ItemFavoriteListener() {
            @Override
            public void onClickItemListenner(Favorite favorite) {
                Product product = new Product(favorite.getProduct_id(),favorite.getCategory_id(),
                        favorite.getCategory_name(), favorite.getProduct_name(), favorite.getProduct_desc(),
                        favorite.getProduct_price(), favorite.getProduct_image());
                Intent intent = new Intent(context , ProductDetailsActivity.class);
                intent.putExtra("product" , product);
                startActivityForResult(intent, Constant.KEY_UPDATE);
            }

            @Override
            public void clickUnFavorite(Favorite favorite) {
                AlertDialog.Builder buider = new AlertDialog.Builder(context).setTitle("Xóa")
                        .setMessage("Bạn muốn xóa sản phẩm khỏi danh mục yêu thích?")
                        .setPositiveButton("Có", (dialogInterface, i) -> {
                            favoritePresenter.unFavorite(favorite);
                            getData();
                        }).setNegativeButton("Không", null);
                AlertDialog alertDialog = buider.create();
                alertDialog.show();
            }

            @Override
            public void clickToSimilar(Favorite favorite) {
                Intent intent = new Intent(context, SimilarActivity.class);
                intent.putExtra("favorite",  favorite);
                startActivity(intent);
            }
        });
        recyclerviewFavorite.setAdapter(favoriteAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Constant.KEY_UPDATE && resultCode == Activity.RESULT_OK){
            favoritePresenter.getFavoriteList(MySharedPreferencesManager.getCustomer(Constant.PREF_KEY_CUSTOMER).getCustomer_id());
        }
    }

    @Override
    public void getFavoriteListNull() {
        recyclerviewFavorite.setVisibility(View.GONE);
        imgNull.setVisibility(View.VISIBLE);
    }
}