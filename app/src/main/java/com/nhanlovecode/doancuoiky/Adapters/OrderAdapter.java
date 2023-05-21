package com.nhanlovecode.doancuoiky.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhanlovecode.doancuoiky.EventClickListener.ItemOrderClickListener;
import com.nhanlovecode.doancuoiky.Models.Order;
import com.nhanlovecode.doancuoiky.Models.OrderDetails;
import com.nhanlovecode.doancuoiky.R;

import java.text.DecimalFormat;
import java.util.List;

/**
 * order_status : 0 ->  Đang chờ duyệt ,  -1 ->  Đơn Hàng Bị Từ Chối ,  1  ->  Đã Duyệt, Đang Vận Chuyển , 2 -> Hoàn Thành Đơn Hàng và chưa đánh giá , 3 -> Đơn bị từ chối bởi khách hàng, 4-> Hoàn thành đơn hàng và đánh giá
 **/
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {
    List<Order> orderList;
    Context context;
    ItemOrderClickListener mItemOrderClickListener;

    public OrderAdapter(List<Order> orderList, Context context ,  ItemOrderClickListener mItemOrderClickListener) {
        this.orderList = orderList;
        this.context = context;
        this.mItemOrderClickListener = mItemOrderClickListener;
    }

    public void setData(List<Order> orderList) {
        this.orderList = orderList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        Order order = orderList.get(position);
        holder.order_code.setText(order.getOrder_code());
        holder.order_date.setText(order.getOrder_date());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        holder.recCartPayment.setLayoutManager(layoutManager);
        holder.recCartPayment.setHasFixedSize(true);
        OrderDetailsAdapter orderDetailsAdapter = new OrderDetailsAdapter(context, order.getOrderDetails());
        holder.recCartPayment.setAdapter(orderDetailsAdapter);
        holder.total_price.setText(decimalFormat.format(order.getTotal_price() + order.getProduct_fee() - order.getProduct_price_coupon()) + "đ");
        holder.total_quantity.setText(order.getTotal_quantity() + " sản phẩm + phí ship");
        /* 4 -> thanh toán khi nhận hàng , 1 -> momo */
        if (order.getPayment().getPayment_method() == 4){
            holder.payment_status.setText("Hình Thức Thanh Toán : Khi Nhận Hàng");
        }else if(order.getPayment().getPayment_method() == 1){
            holder.payment_status.setText("Hình Thức Thanh Toán : Momo");
        }
        if (order.getOrder_status() == 0){
            holder.order_status.setText("Đang chờ duyệt đơn");
            holder.tvStatus.setText("Hủy đơn");
            holder.btnStatus.setBackgroundTintList(ColorStateList.valueOf(R.color.border_color));
        }else if (order.getOrder_status() == -1){
            holder.order_status.setText("Đơn hàng bị từ chối");
            holder.btnStatus.setVisibility(View.GONE);
        } else if (order.getOrder_status() == 1) {
            holder.order_status.setText("Đã duyệt, đang vận chuyển");
            holder.tvStatus.setText("Đã nhận được hàng");
            holder.btnStatus.setBackgroundTintList(ColorStateList.valueOf(R.color.orange));
        }else if(order.getOrder_status() == 2){
            holder.order_status.setText("Đã nhận được hàng");
            holder.tvStatus.setText("Đánh giá");
            holder.btnStatus.setBackgroundTintList(ColorStateList.valueOf(R.color.ucrop_color_blaze_orange));
        }else if(order.getOrder_status() == 3){
            holder.order_status.setText("Đơn hàng bị từ chối bởi khách hàng");
            holder.btnStatus.setVisibility(View.GONE);
        } else if (order.getOrder_status() == 4) {
            holder.order_status.setText("Đã nhận được hàng");
            holder.btnStatus.setVisibility(View.GONE);
        }
        holder.loOrderCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemOrderClickListener.onClickItemOrder(order);
            }
        });
        holder.loInfoProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemOrderClickListener.onClickItemOrder(order);
            }
        });
        holder.btnStatus.setOnClickListener(view -> {
            mItemOrderClickListener.onClickStatus(order);
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        LinearLayout loOrderCode , loInfoProduct;
        TextView order_code, order_date, total_quantity, total_price, order_status, payment_status, tvStatus;
        LinearLayout btnStatus;

        RecyclerView recCartPayment;

        public MyViewHolder(@NonNull View mView) {
            super(mView);
            order_code = mView.findViewById(R.id.tvOrderCode);
            order_date = mView.findViewById(R.id.tvOrderDate);
            total_quantity = mView.findViewById(R.id.total_quantity);
            total_price = mView.findViewById(R.id.total_price);
            order_status = mView.findViewById(R.id.order_status);
            payment_status = mView.findViewById(R.id.paymentStatus);
            tvStatus = mView.findViewById(R.id.tvStatus);
            btnStatus = mView.findViewById(R.id.btnStatus);
            recCartPayment = mView.findViewById(R.id.recCartPayment);
            loOrderCode = mView.findViewById(R.id.loOrderCode);
            loInfoProduct = mView.findViewById(R.id.loInfoProduct);
        }
    }
}
