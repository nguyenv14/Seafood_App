package com.nhanlovecode.doancuoiky.Views.Similar;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseServer.RepositoryAPI;
import com.nhanlovecode.doancuoiky.DatabaseServer.RetrofitClient;
import com.nhanlovecode.doancuoiky.Models.Category;
import com.nhanlovecode.doancuoiky.Models.Favorite;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SimilarPresenter {
    Context context;
    SimilarMVPView similarMVPView;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    RepositoryAPI repositoryAPI = RetrofitClient.getInstance(Constant.BASE_URL).create(RepositoryAPI.class);

    public SimilarPresenter(Context context, SimilarMVPView similarMVPView) {
        this.context = context;
        this.similarMVPView = similarMVPView;
    }

    public void getDataCategoryProduct(int category_id , int product_id){
        compositeDisposable.add(repositoryAPI.getProductByCategory(category_id, product_id)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(productAPI -> {
                    if(productAPI.getStatus_code() == 200){
                        similarMVPView.getDataProductCategorySuccess(productAPI.getData());
                    }else if(productAPI.getStatus_code() == 404){
                        similarMVPView.getDataProductCategoryError();
                    }
                }, throwable -> {
                    Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }));
    }

    public void getDataIntent(Intent intent) {
        Favorite favorite = (Favorite) intent.getSerializableExtra("favorite");
        Category category = (Category) intent.getSerializableExtra("category");
        if (favorite != null){
            similarMVPView.getDataIntentSuccess(favorite.getCategory_id() , favorite.getProduct_id());
        }else if(category != null){
            similarMVPView.getDataIntentSuccess(category.getCategory_id() , -1);
        }else {
            similarMVPView.getDataIntentError();
        }
    }
}
