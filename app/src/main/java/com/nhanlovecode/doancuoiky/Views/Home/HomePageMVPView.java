package com.nhanlovecode.doancuoiky.Views.Home;

import com.nhanlovecode.doancuoiky.Models.Category;
import com.nhanlovecode.doancuoiky.Models.Product;
import com.nhanlovecode.doancuoiky.Models.Slider;

import java.util.List;

public interface HomePageMVPView {
    void getDataSilderSuccess(List<Slider> sliderList);
    void getDataNewProductSuccess(List<Product> productList);
    void getDataTrendingProductSuccess(List<Product> productList);
    void getDataOrderProductSuccess(List<Product> productList);

    void getNextPageProductError();

    void getNextPageProductSuccess(List<Product> productList);

    void getCurrentPageProductSuccess(List<Product> productList , int page);

    void getDataCategoryListSuccess(List<Category> categoryList);
}
