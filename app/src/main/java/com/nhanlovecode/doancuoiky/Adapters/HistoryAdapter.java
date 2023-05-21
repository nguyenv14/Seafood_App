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
import com.nhanlovecode.doancuoiky.EventClickListener.ItemProductClickListener;
import com.nhanlovecode.doancuoiky.Models.History;
import com.nhanlovecode.doancuoiky.Models.Product;
import com.nhanlovecode.doancuoiky.R;

import java.text.DecimalFormat;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {
    Context context;
    List<History> productList;
    ItemProductClickListener mItemProductClickListener;

    public HistoryAdapter(Context context, List<History> productList, ItemProductClickListener mItemProductClickListener) {
        this.context = context;
        this.productList = productList;
        this.mItemProductClickListener = mItemProductClickListener;
    }

    public HistoryAdapter(Context context, List<History> productList) {
        this.context = context;
        this.productList = productList;
        notifyDataSetChanged();
    }

    public void setData(List<History> productList){
        this.productList = productList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        History history = productList.get(position);
        holder.txtNameProduct.setText(history.getProduct_name());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtPriceProduct.setText("Giá: "+decimalFormat.format(history.getProduct_price())+" đ");
        Glide.with(context).load(history.getProduct_image()).into(holder.imgProduct);

        Product product = new Product(history.getProduct_id(), history.getCategory_id(), history.getCategory_name(), history.getProduct_name()
        , history.getProduct_desc(), history.getProduct_price(), history.getProduct_image());

        holder.itemProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemProductClickListener.onClickItemProduct(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView itemProduct;
        TextView txtPriceProduct , txtNameProduct;
        ImageView imgProduct;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemProduct =  itemView.findViewById(R.id.itemProduct);
            txtNameProduct = itemView.findViewById(R.id.txtNameProduct);
            txtPriceProduct = itemView.findViewById(R.id.txtPriceProduct);
            imgProduct = itemView.findViewById(R.id.imgProduct);
        }

    }
}