package com.nhanlovecode.doancuoiky.Views.Similar;

import com.nhanlovecode.doancuoiky.Models.Product;

import java.util.List;

public interface SimilarMVPView {
    public void getDataProductCategorySuccess(List<Product> productList);

    public void getDataProductCategoryError();

    void getDataIntentSuccess(int category_id, int product_id);

    void getDataIntentError();
}
