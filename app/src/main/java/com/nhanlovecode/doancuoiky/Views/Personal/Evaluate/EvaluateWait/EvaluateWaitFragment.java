package com.nhanlovecode.doancuoiky.Views.Personal.Evaluate.EvaluateWait;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.google.gson.Gson;
import com.nhanlovecode.doancuoiky.Adapters.OrderAdapter;
import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.EventClickListener.ItemOrderClickListener;
import com.nhanlovecode.doancuoiky.Models.Order;
import com.nhanlovecode.doancuoiky.R;
import com.nhanlovecode.doancuoiky.Views.LoginAndSignup.Login.Evaluate.EvaluateActivity;
import com.nhanlovecode.doancuoiky.Views.Order.OrderDetails.OrderDetailsActivity;

import java.util.List;


public class EvaluateWaitFragment extends Fragment implements EvaluateWaitMVPViews{
    View view;
    RecyclerView rcEvaluateWait;
    LottieAnimationView imageNull;
    Context context;
    EvaluateWaitPresenter mEvaluateWaitPresenter;
    OrderAdapter orderAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();
        mEvaluateWaitPresenter = new EvaluateWaitPresenter(context, this);
        view = inflater.inflate(R.layout.fragment_evaluate_wait, container, false);
        initUI(view);
        getData();
        return view;
    }

    private void getData() {
        mEvaluateWaitPresenter.getEvaluateWait(Constant.CUSTOMER_ID);
    }

    private void initUI(View view) {
        rcEvaluateWait = view.findViewById(R.id.rcEvaluateWait);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rcEvaluateWait.setLayoutManager(layoutManager);
        rcEvaluateWait.setHasFixedSize(true);
        imageNull = view.findViewById(R.id.imageNull);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Constant.KEY_UPDATE && resultCode == Activity.RESULT_OK){
            getData();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    @Override
    public void getEvaluateWaitSuccess(List<Order> data) {
        rcEvaluateWait.setVisibility(View.VISIBLE);
        imageNull.setVisibility(View.GONE);
        orderAdapter = new OrderAdapter(data, context, new ItemOrderClickListener() {
            @Override
            public void onClickItemOrder(Order order) {
                Gson gson = new Gson();
                String strOrder = gson.toJson(order);
                Intent intent = new Intent(context , OrderDetailsActivity.class);
                intent.putExtra("strOrder", strOrder);
                startActivityForResult(intent, Constant.KEY_UPDATE);
            }

            @Override
            public void onClickStatus(Order order) {
                Intent intent = new Intent(context, EvaluateActivity.class);
                intent.putExtra("order", order);
                startActivityForResult(intent, Constant.KEY_UPDATE);
            }
        });
        rcEvaluateWait.setAdapter(orderAdapter);
    }

    @Override
    public void getEvaluateWaitError() {
        rcEvaluateWait.setVisibility(View.GONE);
        imageNull.setVisibility(View.VISIBLE);
    }
}