package com.nhanlovecode.doancuoiky.Views.Category;

import android.content.Context;
import android.widget.Toast;

import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseServer.RepositoryAPI;
import com.nhanlovecode.doancuoiky.DatabaseServer.RetrofitClient;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CategoryProductPresenter {
    Context context;
    CategoryProductMVPView categoryProductMVPView;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    RepositoryAPI mRepositoryAPI = RetrofitClient.getInstance(Constant.BASE_URL).create(RepositoryAPI.class);

    public CategoryProductPresenter(Context context, CategoryProductMVPView categoryProductMVPView) {
        this.context = context;
        this.categoryProductMVPView = categoryProductMVPView;
    }

    public void getDataCategoryProduct(){
        compositeDisposable.add(mRepositoryAPI.getCategory().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                        categoryAPI -> {
                            categoryProductMVPView.getDataCategoryListSuccess(categoryAPI.getData());
                        }, throwable -> {
                            Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }));
    }



}
