package com.nhanlovecode.doancuoiky.Views.Order.OrderProcessingTab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

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
import com.nhanlovecode.doancuoiky.DatabaseLocal.SharedPreferences.MySharedPreferencesManager;
import com.nhanlovecode.doancuoiky.EventClickListener.ItemOrderClickListener;
import com.nhanlovecode.doancuoiky.Models.Order;
import com.nhanlovecode.doancuoiky.R;
import com.nhanlovecode.doancuoiky.Views.Main.MainActivity;
import com.nhanlovecode.doancuoiky.Views.Order.OrderDetails.OrderDetailsActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;


public class OrderProcessingTabFragment extends Fragment implements OrderProcessingTabMVPView  {


   View mView;
   Context context;
   OrderAdapter mOrderAdapter;

   OrderProcessingTabPresenter mOrderProTabPresenter;
   RecyclerView rcOrderProcessing;
   LottieAnimationView imageNull;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        mOrderProTabPresenter = new OrderProcessingTabPresenter(context , this);
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onMyEventReceived(MainActivity.MyEvent event) {
        String order_code = event.getData();
        mOrderProTabPresenter.searchDataOrderProcessing(order_code);
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_order_processing, container, false);
        initUI(mView);
        getData();
        evenClick();
        
        return mView;
    }


    private void evenClick() {
    }

    private void getData() {
        getDataOrderProcessing();
    }

    private void getDataOrderProcessing() {
        mOrderProTabPresenter.getDataOrderProcessing();
    }

    private void initUI(View mView) {
        imageNull = mView.findViewById(R.id.imageNull);
        rcOrderProcessing = mView.findViewById(R.id.rcOrderProcessing);
        LinearLayoutManager linearManager = new LinearLayoutManager(context);
        rcOrderProcessing.setLayoutManager(linearManager);
    }


    @Override
    public void getDataOrderProcessingSuccess(List<Order> orderList) {
        imageNull.setVisibility(View.GONE);
        rcOrderProcessing.setVisibility(View.VISIBLE);
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
                        .setMessage("Bạn muốn hủy đơn hàng này?")
                        .setPositiveButton("Có", (dialogInterface, i) -> {
                            mOrderProTabPresenter.orderCancel(order.getOrder_code());
                        }).setNegativeButton("không", null);
                AlertDialog alertDialog = buider.create();
                alertDialog.show();
            }
        });
        rcOrderProcessing.setAdapter(mOrderAdapter);
    }

    @Override
    public void getDataOrderProcessingError() {
        imageNull.setVisibility(View.VISIBLE);
        rcOrderProcessing.setVisibility(View.GONE);
//        Toast.makeText(context, "Không Có Dữ Liệu" + MySharedPreferencesManager.getCustomer(Constant.PREF_KEY_CUSTOMER).getCustomer_id(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void searchDataOrderProcessingSuccess(List<Order> orderList) {
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
                            .setMessage("Bạn muốn hủy đơn hàng này?")
                            .setPositiveButton("Có", (dialogInterface, i) -> {
                                mOrderProTabPresenter.orderCancel(order.getOrder_code());
                            }).setNegativeButton("không", null);
                    AlertDialog alertDialog = buider.create();
                    alertDialog.show();
                }
            });
        }
        rcOrderProcessing.setAdapter(mOrderAdapter);
    }

    @Override
    public void searchDataOrderProcessingError() {
        Toast.makeText(context, "Không tìm thấy đơn hàng", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void cancelOrderSuccess() {
        Toast.makeText(context, "Đã hủy đơn hàng thành công", Toast.LENGTH_SHORT).show();
        getDataOrderProcessing();
    }

}