package com.nhanlovecode.doancuoiky.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nhanlovecode.doancuoiky.EventClickListener.ItemCartClickListener;
import com.nhanlovecode.doancuoiky.Models.Cart;
import com.nhanlovecode.doancuoiky.R;

import java.text.DecimalFormat;
import java.util.List;

public class CheckOutAdapter extends RecyclerView.Adapter<CheckOutAdapter.MyViewHolder>{

    Context context;
    List<Cart> cartList;

    public CheckOutAdapter(Context context, List<Cart> cartList) {
        this.context = context;
        this.cartList = cartList;
    }
    public void setData(List<Cart> cartList){
        this.cartList = cartList;
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
        final Cart cart = cartList.get(position);
        Double totalPrice = cart.getProduct_quantity() * cart.getProduct_price();
        Glide.with(context).load(cart.getProduct_image()).into(holder.imgCart);
        holder.txtCartName.setText(cart.getProduct_name());
        holder.txtCartQuantity.setText("Số Lượng : "+cart.getProduct_quantity());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtCartPrice.setText("Giá: "+decimalFormat.format(totalPrice)+" đ");
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imgCart ;
        TextView txtCartName ,txtCartPrice ,txtCartQuantity  ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCart = itemView.findViewById(R.id.imgCart);
            txtCartName = itemView.findViewById(R.id.txtCartName);
            txtCartPrice = itemView.findViewById(R.id.txtCartPrice);
            txtCartQuantity = itemView.findViewById(R.id.txtCartQuantity);
        }
    }
}
