package com.nhanlovecode.doancuoiky.Views.Order.OrderDeliveringTab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.gson.Gson;
import com.nhanlovecode.doancuoiky.Adapters.OrderAdapter;
import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.EventClickListener.ItemOrderClickListener;
import com.nhanlovecode.doancuoiky.Models.Order;
import com.nhanlovecode.doancuoiky.Models.OrderDetails;
import com.nhanlovecode.doancuoiky.R;
import com.nhanlovecode.doancuoiky.Views.Main.MainActivity;
import com.nhanlovecode.doancuoiky.Views.Order.OrderDetails.OrderDetailsActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;


public class OrderDeliveringTabFragment extends Fragment implements OrderDeliveringTabMVPView{

    Context context;
    OrderDeliveringTabPresenter mDeliveringTabPresenter;
    View mView;
    RecyclerView rcOrderDelivery;
    OrderAdapter mOrderAdapter;

    LottieAnimationView imageNull;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        context = getActivity();
        super.onCreate(savedInstanceState);
        mDeliveringTabPresenter = new OrderDeliveringTabPresenter(context , this);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_order_delivery, container, false);
        initUI(mView);
        getData();
        evenClick();
        return mView;

    }

    @Subscribe
    public void onMyEventReceived(MainActivity.MyEvent event) {
        String order_code = event.getData();
        mDeliveringTabPresenter.searchDataOrderDelivering(order_code);

    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void evenClick() {
    }

    private void getData() {
        getDataOrderDelivering();
    }

    private void getDataOrderDelivering() {
        mDeliveringTabPresenter.getDataOrderDelivering();
    }

    private void initUI(View mView) {
        rcOrderDelivery = mView.findViewById(R.id.rcOrderDelivery);
        LinearLayoutManager linearManager = new LinearLayoutManager(context);
        rcOrderDelivery.setLayoutManager(linearManager);
        imageNull = mView.findViewById(R.id.imageNull);
    }

    @Override
    public void getDataOrderDeliveringSuccess(List<Order> orderList) {
        imageNull.setVisibility(View.GONE);
        rcOrderDelivery.setVisibility(View.VISIBLE);
        mOrderAdapter = new OrderAdapter(orderList, context, new ItemOrderClickListener() {
            @Override
            public void onClickItemOrder(Order order) {
                Gson gson = new Gson();
                String strOrder = gson.toJson(order);
                Intent intent = new Intent(context , OrderDetailsActivity.class);
                intent.putExtra("strOrder", strOrder);
                startActivity(intent);
            }

            @Override
            public void onClickStatus(Order order) {
                AlertDialog.Builder buider = new AlertDialog.Builder(context).setTitle("Xóa")
                        .setMessage("Bạn chắc chắn đã nhận được đơn hàng này?")
                        .setPositiveButton("Có", (dialogInterface, i) -> {
                            mDeliveringTabPresenter.orderRecieve(order.getOrder_code());
                        }).setNegativeButton("không", null);
                AlertDialog alertDialog = buider.create();
                alertDialog.show();
            }
        });
        rcOrderDelivery.setAdapter(mOrderAdapter);
    }

    @Override
    public void getDataOrderDeliveringError() {
        imageNull.setVisibility(View.VISIBLE);
        rcOrderDelivery.setVisibility(View.GONE);
        //        Toast.makeText(context, "OrderDelivering Rỗng", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Constant.KEY_UPDATE && resultCode == Activity.RESULT_OK){
            getData();
        }
    }
    @Override
    public void searchDataOrderDeliveringSuccess(List<Order> orderList) {
        if(mOrderAdapter != null){
            mOrderAdapter.setData(orderList);
        }else {
            mOrderAdapter = new OrderAdapter(orderList, context, new ItemOrderClickListener() {
                @Override
                public void onClickItemOrder(Order order) {
                    Gson gson = new Gson();
                    String strOrder = gson.toJson(order);
                    Intent intent = new Intent(context , OrderDetailsActivity.class);
                    intent.putExtra("strOrder", strOrder);
                    startActivity(intent);
                }

                @Override
                public void onClickStatus(Order order) {
                    AlertDialog.Builder buider = new AlertDialog.Builder(context).setTitle("Xóa")
                            .setMessage("Bạn chắc chắn đã nhận được đơn hàng này?")
                            .setPositiveButton("Có", (dialogInterface, i) -> {
                                mDeliveringTabPresenter.orderRecieve(order.getOrder_code());
                            }).setNegativeButton("không", null);
                    AlertDialog alertDialog = buider.create();
                    alertDialog.show();
                }
            });
        }
        rcOrderDelivery.setAdapter(mOrderAdapter);
    }

    @Override
    public void searchDataOrderDeliveringError() {
        Toast.makeText(context, "Không tìm thấy đơn hàng", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getReceiveOrderSuccess() {
        Toast.makeText(context, "Đã nhận đơn hàng, bạn đã có thể đánh giá sản phẩm", Toast.LENGTH_LONG).show();
        getDataOrderDelivering();
    }
}