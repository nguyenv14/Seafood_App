package com.nhanlovecode.doancuoiky.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nhanlovecode.doancuoiky.EventClickListener.ItemCartClickListener;
import com.nhanlovecode.doancuoiky.Models.Cart;
import com.nhanlovecode.doancuoiky.R;


import java.text.DecimalFormat;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder>{

    Context context;
    List<Cart> cartList;
    ItemCartClickListener iClickItemCartListener;
    List<Cart> cartListChecked;

    public CartAdapter(Context context, List<Cart> cartList , List<Cart> cartListChecked, ItemCartClickListener iClickItemCartListener) {
        this.context = context;
        this.cartList = cartList;
        this.cartListChecked = cartListChecked;
        this.iClickItemCartListener = iClickItemCartListener;
    }
    public void setData(List<Cart> cartList , List<Cart> cartListChecked){
        this.cartList = cartList;
        this.cartListChecked = cartListChecked;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Cart cart = cartList.get(position);
        Double totalPrice = cart.getProduct_quantity() * cart.getProduct_price();
        Glide.with(context).load(cart.getProduct_image()).into(holder.imgCart);
        holder.txtCartName.setText(cart.getProduct_name());
        holder.txtCartQuantity.setText(cart.getProduct_quantity()+ "");
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtCartPrice.setText("Giá: "+decimalFormat.format(totalPrice)+" đ");

        if(!cartListChecked.isEmpty()){
            if(cartListChecked.contains(cart)){
                holder.cbCart.setChecked(true);
            }
        }

        holder.imgCartDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean isChecked = holder.cbCart.isChecked();
                iClickItemCartListener.onClickDeleteItemCart(cart,isChecked);
            }
        });


        holder.imgCartPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean isChecked = holder.cbCart.isChecked();
                iClickItemCartListener.onClickPlusItemCart(cart , isChecked);
            }
        });

        holder.imgCartMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean isChecked = holder.cbCart.isChecked();
                iClickItemCartListener.onClickMinusItemCart(cart , isChecked);
            }
        });

        holder.cbCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean isChecked = holder.cbCart.isChecked();
                if(isChecked){
                    iClickItemCartListener.onClickCItemIsCheckedCart(cart);
                }else {
                    iClickItemCartListener.onClickCItemNoCheckedCart(cart);
                }

                Toast.makeText(context, "Huhu", Toast.LENGTH_SHORT).show();

            }
        });


//        holder.cbCart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                // Xử lý sự kiện khi trạng thái của checkbox thay đổi
//                if(isChecked){
//                    iClickItemCartListener.onClickCItemIsCheckedCart(cart);
//                }else {
//                    iClickItemCartListener.onClickCItemNoCheckedCart(cart);
//                }
//
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        CheckBox cbCart;
        ImageView imgCart , imgCartMinus , imgCartDelete , imgCartPlus;
        TextView txtCartName ,txtCartPrice ,txtCartQuantity  ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cbCart =  itemView.findViewById(R.id.cbCart);
            imgCart = itemView.findViewById(R.id.imgCart);
            imgCartMinus = itemView.findViewById(R.id.imgCartMinus);
            imgCartDelete = itemView.findViewById(R.id.imgCartDelete);
            imgCartPlus = itemView.findViewById(R.id.imgCartPlus);
            txtCartName = itemView.findViewById(R.id.txtCartName);
            txtCartPrice = itemView.findViewById(R.id.txtCartPrice);
            txtCartQuantity = itemView.findViewById(R.id.txtCartQuantity);
        }
    }
}
