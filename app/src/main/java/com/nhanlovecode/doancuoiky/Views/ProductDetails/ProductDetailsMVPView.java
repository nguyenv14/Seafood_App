package com.nhanlovecode.doancuoiky.Views.ProductDetails;

import com.nhanlovecode.doancuoiky.Models.Comment;
import com.nhanlovecode.doancuoiky.Models.GalleryProduct;
import com.nhanlovecode.doancuoiky.Models.Product;

import java.util.List;

public interface ProductDetailsMVPView {
    void getDataProductSuccess(Product product);
    void getDataProductError();
    void getDataProductByCategorySuccess(List<Product> productList);
    void getDataProductByCategoryError();
    void getDataOrderProductSuccess(List<Product> productList);
    void getDataOrderProductError();
    void getDataGalleryProductSuccess(List<GalleryProduct> galleryProductList);
    void getDataGalleryProductError();
    void getDataStarProductSuccess(int numStars, Double avgStar, int totalEval);
    void getDataStarProductError();
    void setQuantityProductSuccess(int quantity, Double totalPriceProduct);
    void setQuantityProductError();
    void putCartError();
    void putCartSuccess();
    void actionAddProductToFavorite();
    void actionRemoveProductToFavorite();
    void getDataEvaluateProductSuccess(List<Comment> commentList);

    void getgetDataFavoriteError();

    void getgetDataFavoriteSuccess();
}
