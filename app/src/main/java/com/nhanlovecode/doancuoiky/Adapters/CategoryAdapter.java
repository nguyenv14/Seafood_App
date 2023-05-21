package com.nhanlovecode.doancuoiky.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nhanlovecode.doancuoiky.EventClickListener.ItemCategoryClickListener;
import com.nhanlovecode.doancuoiky.Models.Cart;
import com.nhanlovecode.doancuoiky.Models.Category;
import com.nhanlovecode.doancuoiky.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder>{
    Context context;
    List<Category> categoryList;
    ItemCategoryClickListener itemCategoryClickListener;

    public CategoryAdapter(Context context, List<Category> categoryList, ItemCategoryClickListener itemCategoryClickListener) {
        this.context = context;
        this.categoryList = categoryList;
        this.itemCategoryClickListener = itemCategoryClickListener;
    }

    public void setData(List<Category> categoryList){
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.txtCategoryName.setText(category.getCategory_name());
        Glide.with(context).load(category.getCategory_image()).into(holder.imgCategoryImage);
        holder.loCartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCategoryClickListener.setOnClickItemCategory(category);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (categoryList == null){
            return 0;
        }
        return categoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        CardView loCartView;
        ImageView imgCategoryImage;
        TextView txtCategoryName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            loCartView = itemView.findViewById(R.id.loCartView);
            imgCategoryImage = itemView.findViewById(R.id.imgCategoryImage);
            txtCategoryName = itemView.findViewById(R.id.txtCategoryName);
        }
    }
}
