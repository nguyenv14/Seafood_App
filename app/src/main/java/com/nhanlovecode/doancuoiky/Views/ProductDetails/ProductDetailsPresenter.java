package com.nhanlovecode.doancuoiky.Views.ProductDetails;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseLocal.RoomDatabase.CartDatabase;
import com.nhanlovecode.doancuoiky.DatabaseLocal.RoomDatabase.FavoriteDatabase;
import com.nhanlovecode.doancuoiky.DatabaseLocal.SharedPreferences.MySharedPreferencesManager;
import com.nhanlovecode.doancuoiky.DatabaseServer.RepositoryAPI;
import com.nhanlovecode.doancuoiky.DatabaseServer.RetrofitClient;
import com.nhanlovecode.doancuoiky.Models.Cart;
import com.nhanlovecode.doancuoiky.Models.Comment;
import com.nhanlovecode.doancuoiky.Models.Favorite;
import com.nhanlovecode.doancuoiky.Models.Product;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProductDetailsPresenter {

    CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    RepositoryAPI mRepositoryAPI = RetrofitClient.getInstance(Constant.BASE_URL).create(RepositoryAPI.class);

    Context context;
    ProductDetailsMVPView mProductDetailsMVPView;

    public ProductDetailsPresenter(Context context, ProductDetailsMVPView mProductDetailsMVPView) {
        this.context = context;
        this.mProductDetailsMVPView = mProductDetailsMVPView;
    }

    public void getDataProduct(Intent intent) {
        Product product = (Product) intent.getSerializableExtra("product");
        if (product != null){
            mProductDetailsMVPView.getDataProductSuccess(product);
        }else {
            mProductDetailsMVPView.getDataProductError();
        }
    }

    public void getDataProductByCategory(int category_id, int product_id) {
        mCompositeDisposable.add(mRepositoryAPI.getProductByCategory(category_id, product_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        productAPI -> {
                            if(productAPI.getStatus_code() == 200){
                                mProductDetailsMVPView.getDataProductByCategorySuccess(productAPI.getData());
                            }else if (productAPI.getStatus_code() == 404){
                                mProductDetailsMVPView.getDataProductByCategoryError();
                            }
                        },
                        throwable -> {
                            Toast.makeText(context,"ProCate"+throwable.getMessage(),Toast.LENGTH_LONG).show();
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
                                mProductDetailsMVPView.getDataOrderProductSuccess(productAPI.getData());
                            }else if(productAPI.getStatus_code() == 404){
                                mProductDetailsMVPView.getDataOrderProductError();
                            }
                        },
                        throwable -> {
                            Toast.makeText(context,throwable.getMessage() ,Toast.LENGTH_LONG).show();
                        }
                ));
    }

    public void getDataGalleryProduct(int product_id) {
        mCompositeDisposable.add(mRepositoryAPI.getGalleryProduct(product_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        galleryProductAPI -> {
                            if (galleryProductAPI.getStatus_code() == 200){
                                mProductDetailsMVPView.getDataGalleryProductSuccess(galleryProductAPI.getData());
                            }else if(galleryProductAPI.getStatus_code() == 404){
                                mProductDetailsMVPView.getDataGalleryProductError();
                            }
                        },
                        throwable -> {
                            Log.d("TAG GalleryProduct ", "getGalleryProduct: "+throwable.getMessage());
                            Toast.makeText(context,throwable.getMessage() ,Toast.LENGTH_LONG).show();
                        }
                ));
    }

    public void getDataEvaluateProduct(int product_id) {
        mCompositeDisposable.add(mRepositoryAPI.getCommentProduct(product_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                      commentAPI -> {
                          if(commentAPI.getStatus_code() == 200){
                              handleEvaluate(commentAPI.getData());
                              mProductDetailsMVPView.getDataEvaluateProductSuccess(commentAPI.getData());
                          }else if(commentAPI.getStatus_code() == 404){
                              mProductDetailsMVPView.getDataStarProductError();
                          }
                      },
                        throwable -> {
                            Toast.makeText(context,throwable.getMessage() ,Toast.LENGTH_LONG).show();
                        }
                ));
    }

    private void handleEvaluate(List<Comment> commentList){
        if(commentList != null){
            int totalEval = commentList.size();
            int total_star = 0;
            Double avgStar = 0.0;
            for (Comment data : commentList) {
                total_star = total_star + data.getComment_rate_star();
            }
            avgStar = Double.valueOf(total_star / totalEval);
            avgStar = (double) Math.round(avgStar * 10) / 10; /* Làm tròn số thập phân */

            int numStars = (int) Math.round(avgStar);

            mProductDetailsMVPView.getDataStarProductSuccess(numStars , avgStar , totalEval);

        }
    }

    public void minusQuantityProduct(Product mProduct, int quantity) {
        if (quantity > 1){
            quantity = quantity - 1;
            Double priceProduct = mProduct.getProduct_price();
            Double totalPriceProduct = priceProduct * quantity;
            mProductDetailsMVPView.setQuantityProductSuccess(quantity , totalPriceProduct);
        }else {
            mProductDetailsMVPView.setQuantityProductError();
        }
    }

    public void plusQuantityProduct(Product mProduct, int quantity) {
        if(quantity <= 100){
            quantity = quantity + 1;
            Double priceProduct = mProduct.getProduct_price();
            Double totalPriceProduct = priceProduct * quantity;
            mProductDetailsMVPView.setQuantityProductSuccess(quantity , totalPriceProduct);
        }else {
            mProductDetailsMVPView.setQuantityProductError();
        }
    }

    public void addCart(Cart cart) {
        List<Cart> cartList =  CartDatabase.getInstance(context).cartDAO().checkCart(cart.getProduct_id());
        if (cartList.size() >= 1){
            mProductDetailsMVPView.putCartError();
        }else {
            CartDatabase.getInstance(context).cartDAO().InsertCart(cart);
            mProductDetailsMVPView.putCartSuccess();
        }
    }
    public void addProductToFavorite(Product product){
        int customer_id = MySharedPreferencesManager.getCustomer(Constant.PREF_KEY_CUSTOMER).getCustomer_id();
        Favorite favorite = new Favorite(customer_id, product.getProduct_id(), product.getCategory_id(), product.getCategory_name(), product.getProduct_name(),
                product.getProduct_desc(), product.getProduct_price(), product.getProduct_image());
        FavoriteDatabase.getInstance(context).favoriteDAO().insertFavorite(favorite);
        mProductDetailsMVPView.actionAddProductToFavorite();
    }
    public void removeProductToFavorite(Product product){
        int customer_id = MySharedPreferencesManager.getCustomer(Constant.PREF_KEY_CUSTOMER).getCustomer_id();
        Favorite favorite = FavoriteDatabase.getInstance(context).favoriteDAO().getFavorite(product.getProduct_id(), customer_id);
        FavoriteDatabase.getInstance(context).favoriteDAO().deleteFavorite(favorite);
        mProductDetailsMVPView.actionRemoveProductToFavorite();
    }


    public void getDataFavorite(int product_id) {
        Favorite favorite = FavoriteDatabase.getInstance(context).favoriteDAO().getFavorite(product_id,
                MySharedPreferencesManager.getCustomer(Constant.PREF_KEY_CUSTOMER).getCustomer_id());
        if (favorite == null){
            mProductDetailsMVPView.getgetDataFavoriteError();
        }else {
            mProductDetailsMVPView.getgetDataFavoriteSuccess();
        }
    }
}
