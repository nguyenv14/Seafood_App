package com.nhanlovecode.doancuoiky.Views.Search;

import android.content.Context;
import android.widget.Toast;

import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseServer.RepositoryAPI;
import com.nhanlovecode.doancuoiky.DatabaseServer.RetrofitClient;
import com.nhanlovecode.doancuoiky.Models.Category;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchActivityPresenter {
    Context context;
    SearchActivityMVPView searchActivityMVPView;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    RepositoryAPI repositoryAPI = RetrofitClient.getInstance(Constant.BASE_URL).create(RepositoryAPI.class);

    public SearchActivityPresenter(Context context, SearchActivityMVPView searchActivityMVPView) {
        this.context = context;
        this.searchActivityMVPView = searchActivityMVPView;
    }

    public void getDataNameSpinnerCategory(){
        List<String> strings = new ArrayList<String>();
        compositeDisposable.add(repositoryAPI.getCategory().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(categoryAPI -> {
                    for( Category data : categoryAPI.getData()){
                        strings.add(data.getCategory_name());
                    }
                    searchActivityMVPView.getDataCategoryNameSuccess(strings);
                }, throwable -> {
                    Toast.makeText(context, "Category: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }));
    }

    public void getDataProductToSearchView(){
        compositeDisposable.add(repositoryAPI.getListAllProduct().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(productAPI -> {
                    if(productAPI.getStatus_code() == 200){
                        searchActivityMVPView.getDataListAllProductSuccess(productAPI.getData());
                    }else if(productAPI.getStatus_code() == 404){
                        searchActivityMVPView.getDataListAllProductError();
                    }
                }, throwable -> {
                    Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }));
    }

    public void searchProduct(String text, String nameCategory, int numberFilter, float priceMin, float priceMax){
        compositeDisposable.add(repositoryAPI.getListProductBySearch(text, nameCategory, numberFilter, priceMin, priceMax).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(productAPI -> {
                    if(productAPI.getStatus_code() == 200){
                        searchActivityMVPView.getDataListAllProductSuccess(productAPI.getData());
                    }else if(productAPI.getStatus_code() == 404){
                        searchActivityMVPView.getDataListAllProductError();
                    }
                }, throwable -> {
                    Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }));
    }

    public void getPriceMinPriceMax(){
        compositeDisposable.add(repositoryAPI.getPriceMinPriceMax().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(productAPI -> {
                    searchActivityMVPView.getDataPriceMinAndPriceMaxSuccess(productAPI.getData().get(0), productAPI.getData().get(1));
                }, throwable -> {
                    Toast.makeText(context, "Price: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }));
    }
}
