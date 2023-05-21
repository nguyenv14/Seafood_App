package com.nhanlovecode.doancuoiky.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nhanlovecode.doancuoiky.Models.GalleryProduct;
import com.nhanlovecode.doancuoiky.R;


import java.util.List;

public class ImageDetailsProductAdapter extends RecyclerView.Adapter<ImageDetailsProductAdapter.MyViewHolder> {
    Context context;
    List<GalleryProduct> gallaryProductList;

    public ImageDetailsProductAdapter(Context context, List<GalleryProduct> gallaryProductList) {
        this.context = context;
        this.gallaryProductList = gallaryProductList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_img_details_product,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GalleryProduct gallaryProduct = gallaryProductList.get(position);
        Glide.with(context).load(gallaryProduct.getGallery_product_image()).into(holder.imgGallery);
        if(!gallaryProduct.getGallery_product_content().equals("Ảnh này chưa có nội dung !")){
            holder.tvGalleryContent.setText(gallaryProduct.getGallery_product_content());
        }
    }

    @Override
    public int getItemCount() {
        return gallaryProductList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imgGallery;
        TextView tvGalleryContent;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgGallery = itemView.findViewById(R.id.imgGallery);
            tvGalleryContent = itemView.findViewById(R.id.tvGalleryContent);
        }
    }

}
