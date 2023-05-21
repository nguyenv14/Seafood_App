package com.nhanlovecode.doancuoiky.Views.ProductCategory;

import com.nhanlovecode.doancuoiky.Models.Product;

import java.util.List;

public interface ProductCategoryMVPView {
    void getCategoryIdError();

    void getCategoryIdSuccess(int category_id);

    void getDataProductByCategoryError();

    void getDataProductByCategorySuccess(List<Product> productList);
}
