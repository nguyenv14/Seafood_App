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
import com.nhanlovecode.doancuoiky.Models.Cart;
import com.nhanlovecode.doancuoiky.Models.OrderDetails;
import com.nhanlovecode.doancuoiky.R;

import java.text.DecimalFormat;
import java.util.List;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.MyViewHolder>{
    Context context;
    List<OrderDetails> orderDetailsList;

    public OrderDetailsAdapter(Context context, List<OrderDetails> orderDetailsList) {
        this.context = context;
        this.orderDetailsList = orderDetailsList;
    }
    public void setData(List<OrderDetails> orderDetailsList){
       this.orderDetailsList = orderDetailsList;
       notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final OrderDetails orderDetails = orderDetailsList.get(position);
        Double totalPrice = orderDetails.getProduct_sales_quantity() * orderDetails.getProduct_price();
        Glide.with(context).load(orderDetails.getProduct_image()).into(holder.imgCart);
        holder.txtCartName.setText(orderDetails.getProduct_name());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtCartPrice.setText("Giá: "+decimalFormat.format(totalPrice)+" đ");
        holder.txtCartQuantity.setText("Số lượng: " + orderDetails.getProduct_sales_quantity());
    }

    @Override
    public int getItemCount() {
        return orderDetailsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCart;
        TextView txtCartName, txtCartPrice, txtCartQuantity;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCart = itemView.findViewById(R.id.imgCart);
            txtCartName = itemView.findViewById(R.id.txtCartName);
            txtCartPrice = itemView.findViewById(R.id.txtCartPrice);
            txtCartQuantity = itemView.findViewById(R.id.txtCartQuantity);
        }
    }

}