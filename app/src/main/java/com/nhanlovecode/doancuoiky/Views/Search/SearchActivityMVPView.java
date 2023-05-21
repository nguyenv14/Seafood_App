package com.nhanlovecode.doancuoiky.Views.Search;

import com.nhanlovecode.doancuoiky.Models.Product;

import java.util.List;

public interface SearchActivityMVPView {


    void getDataCategoryNameSuccess(List<String> stringList);

    void getDataListAllProductSuccess(List<Product> productList);
    void getDataListAllProductError();

    void getDataPriceMinAndPriceMaxSuccess(Product productMin, Product productMax);
}
