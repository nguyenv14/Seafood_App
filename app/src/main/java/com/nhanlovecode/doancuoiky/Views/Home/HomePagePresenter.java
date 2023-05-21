package com.nhanlovecode.doancuoiky.Views.Home;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.nhanlovecode.doancuoiky.Adapters.ProductAdapter;
import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseServer.RepositoryAPI;
import com.nhanlovecode.doancuoiky.DatabaseServer.RetrofitClient;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePagePresenter {
    CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    RepositoryAPI mRepositoryAPI = RetrofitClient.getInstance(Constant.BASE_URL).create(RepositoryAPI.class);

    Context context;
    HomePageMVPView mHomePageMVPView;


    public HomePagePresenter(Context context, HomePageMVPView mHomePageMVPView) {
        this.context = context;
        this.mHomePageMVPView = mHomePageMVPView;
    }


    public void getDataSlider() {
        mCompositeDisposable.add(mRepositoryAPI.getSlider()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sliderAPI -> {
                            if(sliderAPI.getStatus_code() == 200){
                                mHomePageMVPView.getDataSilderSuccess(sliderAPI.getData());

                            }else if (sliderAPI.getStatus_code() == 404){

                            }
                        },
                        throwable -> {
                            Toast.makeText(context,"Slider"+throwable.getMessage() ,Toast.LENGTH_LONG).show();
                        }
                ));
    }

    public void getDataNewProduct() {
        mCompositeDisposable.add(mRepositoryAPI.getNewProduct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        productAPI -> {
                            if (productAPI.getStatus_code() == 200){
                                mHomePageMVPView.getDataNewProductSuccess(productAPI.getData());
                            }
                        },
                        throwable -> {
                            Toast.makeText(context,"New Product"+throwable.getMessage() ,Toast.LENGTH_LONG).show();
                        }
                ));
    }

    public void getDataTrendingProduct() {
        mCompositeDisposable.add(mRepositoryAPI.getTrendingProduct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        productAPI -> {
                            if (productAPI.getStatus_code() == 200){
                                mHomePageMVPView.getDataTrendingProductSuccess(productAPI.getData());
                            }
                        },
                        throwable -> {
                            Toast.makeText(context,"Trending Product"+throwable.getMessage() ,Toast.LENGTH_LONG).show();
                        }
                ));
    }

    public void getDataOrderProduct() {
        mCompositeDisposable.add(mRepositoryAPI.getOrderProduct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        productAPI -> {
                            if (productAPI.getStatus_code() == 200){
                                mHomePageMVPView.getDataOrderProductSuccess(productAPI.getData());
                            }
                        },
                        throwable -> {
                            Toast.makeText(context,"Trending Product"+throwable.getMessage() ,Toast.LENGTH_LONG).show();
                        }
                ));
    }

    public void getNextPageProduct(int page) {
        mCompositeDisposable.add(mRepositoryAPI.getProduct(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        productAPI -> {
                            if(productAPI.getStatus_code() == 200) {
                                mHomePageMVPView.getNextPageProductSuccess(productAPI.getData());
                            }else{
                                mHomePageMVPView.getNextPageProductError();
                            }
                        },
                        throwable -> {
                            Toast.makeText(context,throwable.getMessage(),Toast.LENGTH_LONG).show();
                        }
                ));
    }

    public void getCurrentPageProduct(int page) {
        mCompositeDisposable.add(mRepositoryAPI.getProduct(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        productAPI -> {
                            if(productAPI.getStatus_code() == 200){
                                mHomePageMVPView.getCurrentPageProductSuccess(productAPI.getData() , page);
                            }
                        },
                        throwable -> {
                            Toast.makeText(context,throwable.getMessage(),Toast.LENGTH_LONG).show();
                        }
                ));
    }

    public void getDataCategory() {
        mCompositeDisposable.add(mRepositoryAPI.getCategory().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                        categoryAPI -> {
                            mHomePageMVPView.getDataCategoryListSuccess(categoryAPI.getData());
                        }, throwable -> {
                            Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }));
    }
}
