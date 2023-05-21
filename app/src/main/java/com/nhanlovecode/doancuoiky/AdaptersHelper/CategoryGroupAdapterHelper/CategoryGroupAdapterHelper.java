package com.nhanlovecode.doancuoiky.AdaptersHelper.CategoryGroupAdapterHelper;

import android.content.Context;
import android.widget.Toast;

import com.nhanlovecode.doancuoiky.Adapters.CategoryGroupAdapter;
import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseServer.RepositoryAPI;
import com.nhanlovecode.doancuoiky.DatabaseServer.RetrofitClient;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CategoryGroupAdapterHelper {

    Context context;
    ICategoryGroupAdapterHelper iCategoryGroupAdapterHelper;

    CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    RepositoryAPI repositoryAPI = RetrofitClient.getInstance(Constant.BASE_URL).create(RepositoryAPI.class);

    public CategoryGroupAdapterHelper(Context context, ICategoryGroupAdapterHelper iCategoryGroupAdapterHelper) {
        this.context = context;
        this.iCategoryGroupAdapterHelper = iCategoryGroupAdapterHelper;
    }

    public void getProductByCategoryGroup(CategoryGroupAdapter.CategoryGroupViewHolder holder, int category_id) {
        mCompositeDisposable.add(repositoryAPI.getListProductByCategoryId(category_id)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        productAPI -> {
                                if(productAPI.getStatus_code() == 200){
                                    iCategoryGroupAdapterHelper.getProductByCategoryGroupSuccess(holder , productAPI.getData());
                            }
                        }, throwable -> {
                            Toast.makeText(context, category_id + throwable.getMessage() , Toast.LENGTH_SHORT).show();
                        }));
    }
}
