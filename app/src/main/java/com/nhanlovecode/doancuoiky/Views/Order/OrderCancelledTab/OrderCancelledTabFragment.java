package com.nhanlovecode.doancuoiky.Views.Order.OrderCancelledTab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.gson.Gson;
import com.nhanlovecode.doancuoiky.Adapters.OrderAdapter;
import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.EventClickListener.ItemOrderClickListener;
import com.nhanlovecode.doancuoiky.Models.Order;
import com.nhanlovecode.doancuoiky.Models.OrderDetails;
import com.nhanlovecode.doancuoiky.R;
import com.nhanlovecode.doancuoiky.Views.Main.MainActivity;
import com.nhanlovecode.doancuoiky.Views.Order.OrderCompletedTab.OrderCompletedTabMVPView;
import com.nhanlovecode.doancuoiky.Views.Order.OrderCompletedTab.OrderCompletedTabPresenter;
import com.nhanlovecode.doancuoiky.Views.Order.OrderDetails.OrderDetailsActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;


public class OrderCancelledTabFragment extends Fragment implements OrderCancelledTabMVPView {

    Context context;
    OrderCancelledTabPresenter mCancelledTabPresenter;
    View mView;
    RecyclerView rcOrderCancelled;
    OrderAdapter mOrderAdapter;

    LottieAnimationView imageNull;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        mCancelledTabPresenter = new OrderCancelledTabPresenter(context , this);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_order_cancelled, container, false);
        initUI(mView);
        getData();
        evenClick();
        return mView;


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Constant.KEY_UPDATE && resultCode == Activity.RESULT_OK){
            getData();
        }
    }

    @Subscribe
    public void onMyEventReceived(MainActivity.MyEvent event) {
        String order_code = event.getData();
        mCancelledTabPresenter.searchDataOrderCancelled(order_code);

    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void evenClick() {
    }

    private void getData() {
        getDataOrderCancelled();
    }

    private void getDataOrderCancelled() {
        mCancelledTabPresenter.getDataOrderCancelled();
    }

    private void initUI(View mView) {
        rcOrderCancelled = mView.findViewById(R.id.rcOrderCancelled);
        LinearLayoutManager linearManager = new LinearLayoutManager(context);
        rcOrderCancelled.setLayoutManager(linearManager);
        imageNull = mView.findViewById(R.id.imageNull);
    }

    @Override
    public void getDataOrderCancelledSuccess(List<Order> orderList) {
        rcOrderCancelled.setVisibility(View.VISIBLE);
        imageNull.setVisibility(View.GONE);
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

            }
        });
        rcOrderCancelled.setAdapter(mOrderAdapter);
    }

    @Override
    public void getDataOrderCancelledError() {
        rcOrderCancelled.setVisibility(View.GONE);
        imageNull.setVisibility(View.VISIBLE);
    }

    @Override
    public void searchDataOrderCancelledSuccess(List<Order> orderList) {
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

                }
            });
        }
        rcOrderCancelled.setAdapter(mOrderAdapter);
    }

    @Override
    public void searchDataOrderCancelledError() {

    }
}