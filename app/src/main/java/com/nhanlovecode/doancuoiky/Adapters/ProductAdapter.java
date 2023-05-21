package com.nhanlovecode.doancuoiky.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nhanlovecode.doancuoiky.AdaptersHelper.CategoryGroupAdapterHelper.CategoryGroupAdapterHelper;
import com.nhanlovecode.doancuoiky.AdaptersHelper.CategoryGroupAdapterHelper.ICategoryGroupAdapterHelper;
import com.nhanlovecode.doancuoiky.AdaptersHelper.ProductAdapterHelper.IProductAdapterHelper;
import com.nhanlovecode.doancuoiky.AdaptersHelper.ProductAdapterHelper.ProductAdapterHelper;
import com.nhanlovecode.doancuoiky.EventClickListener.ItemProductClickListener;
import com.nhanlovecode.doancuoiky.Models.Product;
import com.nhanlovecode.doancuoiky.R;
import com.nhanlovecode.doancuoiky.Views.ProductDetails.ProductDetailsActivity;


import java.text.DecimalFormat;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder>{
    Context context;
    int TYPE_VIEW_ICON = 0;
    List<Product> productList;
    ItemProductClickListener mItemProductClickListener;
    ProductAdapterHelper mProductAdapterHelper;


    public ProductAdapter(Context context, List<Product> productList, ItemProductClickListener mItemProductClickListener) {
        this.context = context;
        this.productList = productList;
        this.mItemProductClickListener = mItemProductClickListener;
    }

    public ProductAdapter(Context context, int TYPE_VIEW_ICON, List<Product> productList, ItemProductClickListener mItemProductClickListener) {
        this.context = context;
        this.TYPE_VIEW_ICON = TYPE_VIEW_ICON;
        this.productList = productList;
        this.mItemProductClickListener = mItemProductClickListener;
    }

    public void setData(List<Product> productList){
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
        Product product = productList.get(position);
        holder.txtNameProduct.setText(product.getProduct_name());
        holder.txtNameCategory.setText(product.getCategory_name());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtPriceProduct.setText(decimalFormat.format(product.getProduct_price())+" đ");
        Glide.with(context).load(product.getProduct_image()).into(holder.imgProduct);

        if (product.getStatus_order().equals("")){
            holder.txtStatusOrder.setVisibility(View.INVISIBLE);
        }else {
            holder.txtStatusOrder.setText(product.getStatus_order());
        }

        if (TYPE_VIEW_ICON == 0){
            holder.imgIconItem.setVisibility(View.GONE);
        }else  if (TYPE_VIEW_ICON == 1){
            holder.imgIconItem.setImageResource(R.mipmap.newproduct);
        }else if (TYPE_VIEW_ICON == 2){
            holder.imgIconItem.setImageResource(R.mipmap.trendingproduct);
        }else if (TYPE_VIEW_ICON == 3){
            holder.imgIconItem.setImageResource(R.mipmap.hotproduct);
        }

        holder.itemProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemProductClickListener.onClickItemProduct(product);
            }
        });

        mProductAdapterHelper = new ProductAdapterHelper(context, new IProductAdapterHelper() {
            @Override
            public void getDataStarProductError(MyViewHolder holder) {
                holder.txtnumStars.setText("0");
                holder.totalEval.setText("(0 đánh giá)");
                holder.loRate.setVisibility(View.VISIBLE);
            }

            @Override
            public void getDataStarProductSuccess(MyViewHolder holder, int numStars, Double avgStar, int totalEval) {
                holder.txtnumStars.setText(numStars+"");
                holder.totalEval.setText("("+totalEval + " đánh giá)");
                holder.loRate.setVisibility(View.VISIBLE);
            }

            @Override
            public void getProductUnitSuccess(MyViewHolder holder,String output) {
                holder.txtProductUnit.setText(output);
            }
        });
        mProductAdapterHelper.putEvaluate(holder , product.getCommentList());
        mProductAdapterHelper.putProductUnit(holder , product.getProduct_unit() , product.getProduct_unit_sold());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout loRate;
        CardView itemProduct;
        TextView txtPriceProduct , txtNameProduct , txtNameCategory , txtnumStars , totalEval , txtStatusOrder , txtProductUnit;
        ImageView imgProduct, imgIconItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            loRate  =  itemView.findViewById(R.id.loRate);
            loRate.setVisibility(View.INVISIBLE);
            itemProduct =  itemView.findViewById(R.id.itemProduct);
            txtNameProduct = itemView.findViewById(R.id.txtNameProduct);
            txtPriceProduct = itemView.findViewById(R.id.txtPriceProduct);
            txtNameCategory = itemView.findViewById(R.id.txtNameCategory);
            txtnumStars = itemView.findViewById(R.id.txtnumStars);
            totalEval = itemView.findViewById(R.id.totalEval);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            imgIconItem = itemView.findViewById(R.id.imgIconItem);
            txtStatusOrder = itemView.findViewById(R.id.txtStatusOrder);
            txtProductUnit = itemView.findViewById(R.id.txtProductUnit);
        }

    }
}
