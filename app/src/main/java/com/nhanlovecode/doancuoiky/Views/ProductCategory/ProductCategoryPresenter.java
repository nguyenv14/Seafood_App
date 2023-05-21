package com.nhanlovecode.doancuoiky.Views.ProductCategory;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseServer.RepositoryAPI;
import com.nhanlovecode.doancuoiky.DatabaseServer.RetrofitClient;
import com.nhanlovecode.doancuoiky.Utils.Utils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProductCategoryPresenter {
    CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    RepositoryAPI mRepositoryAPI = RetrofitClient.getInstance(Constant.BASE_URL).create(RepositoryAPI.class);

    Context context;
    ProductCategoryMVPView mProductCategoryMVPView;

    public ProductCategoryPresenter(Context context, ProductCategoryMVPView mProductCategoryMVPView) {
        this.context = context;
        this.mProductCategoryMVPView = mProductCategoryMVPView;
    }

    public void getCategoryID(Intent intent) {
        int category_id = intent.getIntExtra("category_id",-1);
        if (category_id == -1){
            mProductCategoryMVPView.getCategoryIdError();
        }else {
            mProductCategoryMVPView.getCategoryIdSuccess(category_id);
        }
    }

    public void getProductByCategory(int category_id) {
        mCompositeDisposable.add(mRepositoryAPI.getListProductByCategoryId(category_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        productAPI -> {
                            if(productAPI.getStatus_code() == 200){
                                mProductCategoryMVPView.getDataProductByCategorySuccess(productAPI.getData());
                            }else if (productAPI.getStatus_code() == 404){
                                mProductCategoryMVPView.getDataProductByCategoryError();
                            }
                        },
                        throwable -> {
                            Toast.makeText(context,throwable.getMessage(),Toast.LENGTH_LONG).show();
                        }
                ));
    }
}
