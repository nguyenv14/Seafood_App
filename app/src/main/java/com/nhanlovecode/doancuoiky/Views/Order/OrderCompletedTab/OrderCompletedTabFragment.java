package com.nhanlovecode.doancuoiky.Views.Order.OrderCompletedTab;

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
import com.nhanlovecode.doancuoiky.R;
import com.nhanlovecode.doancuoiky.Views.LoginAndSignup.Login.Evaluate.EvaluateActivity;
import com.nhanlovecode.doancuoiky.Views.Main.MainActivity;
import com.nhanlovecode.doancuoiky.Views.Order.OrderDetails.OrderDetailsActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;


public class OrderCompletedTabFragment extends Fragment implements OrderCompletedTabMVPView {

    Context context;
    OrderCompletedTabPresenter mCompletedTabPresenter;
    View mView;
    RecyclerView rcOrderCompleted;
    OrderAdapter mOrderAdapter;

    LottieAnimationView imageNull;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        mCompletedTabPresenter = new OrderCompletedTabPresenter(context , this);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_order_completed, container, false);
        initUI(mView);
        getData();
        evenClick();
        return mView;


    }

    @Subscribe
    public void onMyEventReceived(MainActivity.MyEvent event) {
        String order_code = event.getData();
        mCompletedTabPresenter.searchDataOrderCompleted(order_code);
    }




    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void evenClick() {
    }

    private void getData() {
        getDataOrderCompleted();
    }

    private void getDataOrderCompleted() {
        mCompletedTabPresenter.getDataOrderCompleted();
    }

    private void initUI(View mView) {
        rcOrderCompleted = mView.findViewById(R.id.rcOrderCompleted);
        LinearLayoutManager linearManager = new LinearLayoutManager(context);
        rcOrderCompleted.setLayoutManager(linearManager);
        imageNull = mView.findViewById(R.id.imageNull);
    }

    @Override
    public void getDataOrderCompletedSuccess(List<Order> orderList) {
        imageNull.setVisibility(View.GONE);
        rcOrderCompleted.setVisibility(View.VISIBLE);
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
                Intent intent = new Intent(context, EvaluateActivity.class);
                intent.putExtra("order", order);
                startActivityForResult(intent, Constant.KEY_UPDATE);
            }
        });
        rcOrderCompleted.setAdapter(mOrderAdapter);
    }

    @Override
    public void getDataOrderCompletedError() {
        imageNull.setVisibility(View.VISIBLE);
        rcOrderCompleted.setVisibility(View.GONE);
//        Toast.makeText(context, "Completed Rỗng", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void searchDataOrderCompletedSuccess(List<Order> orderList) {
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
        rcOrderCompleted.setAdapter(mOrderAdapter);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Constant.KEY_UPDATE && resultCode == Activity.RESULT_OK){
            getData();
        }
    }
    @Override
    public void searchDataOrderCompletedError() {

    }
}