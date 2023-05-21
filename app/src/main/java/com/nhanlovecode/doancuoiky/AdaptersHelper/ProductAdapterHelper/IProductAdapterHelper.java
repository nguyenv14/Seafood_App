package com.nhanlovecode.doancuoiky.AdaptersHelper.ProductAdapterHelper;

import com.nhanlovecode.doancuoiky.Adapters.ProductAdapter;

public interface IProductAdapterHelper {
    void getDataStarProductError(ProductAdapter.MyViewHolder holder);

    void getDataStarProductSuccess(ProductAdapter.MyViewHolder holder, int numStars, Double avgStar, int totalEval);

    void getProductUnitSuccess(ProductAdapter.MyViewHolder holder,String output);
}
