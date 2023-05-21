package com.nhanlovecode.doancuoiky.Views.Personal.Evaluate.EvaluateHave;

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
import com.nhanlovecode.doancuoiky.Views.Order.OrderDetails.OrderDetailsActivity;

import java.util.List;


public class EvaluateHaveFragment extends Fragment implements EvaluateHaveMVPViews {
    View view;
    Context context;
    RecyclerView rcEvaluateHave;

    LottieAnimationView imageNull;
    EvaluateHavePresenter mEvaluateHavePresenter;
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
        mEvaluateHavePresenter = new EvaluateHavePresenter(context, this);
        view = inflater.inflate(R.layout.fragment_evaluate_have, container, false);
        initUI(view);
        getData();
        return view;
    }

    private void getData() {
        mEvaluateHavePresenter.getEvaluateHave(Constant.CUSTOMER_ID);
    }

    private void initUI(View view) {
        rcEvaluateHave = view.findViewById(R.id.rcEvaluateHave);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rcEvaluateHave.setLayoutManager(layoutManager);
        rcEvaluateHave.setHasFixedSize(true);
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
    public void getEvaluateHaveSuccess(List<Order> orderList) {
        rcEvaluateHave.setVisibility(View.VISIBLE);
        imageNull.setVisibility(View.GONE);
        orderAdapter = new OrderAdapter(orderList, context, new ItemOrderClickListener() {
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

            }
        });
        rcEvaluateHave.setAdapter(orderAdapter);
    }

    @Override
    public void getEvaluateHaveError() {
        rcEvaluateHave.setVisibility(View.GONE);
        imageNull.setVisibility(View.VISIBLE);
    }
}