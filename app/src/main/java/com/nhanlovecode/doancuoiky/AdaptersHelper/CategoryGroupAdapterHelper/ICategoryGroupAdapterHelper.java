package com.nhanlovecode.doancuoiky.AdaptersHelper.CategoryGroupAdapterHelper;

import com.nhanlovecode.doancuoiky.Adapters.CategoryGroupAdapter;
import com.nhanlovecode.doancuoiky.Models.Product;

import java.util.List;

public interface ICategoryGroupAdapterHelper {
    void getProductByCategoryGroupSuccess(CategoryGroupAdapter.CategoryGroupViewHolder holder, List<Product> productList);
}
