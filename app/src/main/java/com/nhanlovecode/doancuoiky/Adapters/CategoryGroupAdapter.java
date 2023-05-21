package com.nhanlovecode.doancuoiky.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.nhanlovecode.doancuoiky.AdaptersHelper.CategoryGroupAdapterHelper.CategoryGroupAdapterHelper;
import com.nhanlovecode.doancuoiky.AdaptersHelper.CategoryGroupAdapterHelper.ICategoryGroupAdapterHelper;
import com.nhanlovecode.doancuoiky.EventClickListener.ItemProductClickListener;
import com.nhanlovecode.doancuoiky.Models.Category;
import com.nhanlovecode.doancuoiky.Models.Product;
import com.nhanlovecode.doancuoiky.R;
import com.nhanlovecode.doancuoiky.Views.ProductDetails.ProductDetailsActivity;

import java.util.List;

public class CategoryGroupAdapter extends RecyclerView.Adapter<CategoryGroupAdapter.CategoryGroupViewHolder> {
    Context context;
    List<Category> categoryList;
    CategoryGroupAdapterHelper mCategoryGroupAdapterHelper ;
    public CategoryGroupAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_group, parent, false);
        return new CategoryGroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryGroupViewHolder holder, int position) {
        Category category = categoryList.get(position);
        Glide.with(context).load(category.getCategory_image()).into(holder.imageCategory);
        holder.tvCategoryName.setText(category.getCategory_name());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 2);
        holder.recyclerViewCategoryGroup.setLayoutManager(layoutManager);
        holder.recyclerViewCategoryGroup.setHasFixedSize(true);
        new Handler().postDelayed(
                new Runnable() {
                @Override
                public void run(){
                     mCategoryGroupAdapterHelper = new CategoryGroupAdapterHelper(context, new ICategoryGroupAdapterHelper() {
                         @Override
                         public void getProductByCategoryGroupSuccess(CategoryGroupViewHolder holder, List<Product> productList) {
                             ProductAdapter productAdapter = new ProductAdapter(context, productList, new ItemProductClickListener() {
                                 @Override
                                 public void onClickItemProduct(Product product) {
                                     Intent intent = new Intent(context, ProductDetailsActivity.class);
                                     intent.putExtra("product", product);
                                     context.startActivity(intent);
                                 }
                             });
                             holder.recyclerViewCategoryGroup.setAdapter(productAdapter);
                         }
                     });
                     mCategoryGroupAdapterHelper.getProductByCategoryGroup(holder , category.getCategory_id());
                }
        },550);

    }
    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryGroupViewHolder extends RecyclerView.ViewHolder{
        ImageView imageCategory;
        TextView tvCategoryName;
        RecyclerView recyclerViewCategoryGroup;
        LottieAnimationView imageNull;
        public CategoryGroupViewHolder(@NonNull View itemView) {
            super(itemView);
            imageCategory = itemView.findViewById(R.id.imageCategory);
            tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
            recyclerViewCategoryGroup = itemView.findViewById(R.id.recyclerViewCategoryProductGroup);
            imageNull = itemView.findViewById(R.id.imageNull);
        }
    }
}
