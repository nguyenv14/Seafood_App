package com.nhanlovecode.doancuoiky.Views.Personal.History;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.nhanlovecode.doancuoiky.Adapters.HistoryAdapter;
import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseLocal.RoomDatabase.HistoryDatabase;
import com.nhanlovecode.doancuoiky.EventClickListener.ItemProductClickListener;
import com.nhanlovecode.doancuoiky.Models.History;
import com.nhanlovecode.doancuoiky.Models.Product;
import com.nhanlovecode.doancuoiky.R;
import com.nhanlovecode.doancuoiky.Views.ProductDetails.ProductDetailsActivity;

import java.util.List;

public class HistoryActivity extends AppCompatActivity implements HistoryMVPView{
    RecyclerView recyclerViewHistory;
    Context context;
    LottieAnimationView imageNull;
    ImageView btnBack;
    HistoryAdapter historyAdapter;

    HistoryPresenter historyPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        context = this;
        historyPresenter = new HistoryPresenter(this, this);
        initUI();
        setData();
        eventClick();
    }

    private void eventClick() {
        btnBack.setOnClickListener(view -> {
            finish();
        });
    }

    private void setData() {
        historyPresenter.getListDataHistory();
    }

    private void initUI() {
        recyclerViewHistory = findViewById(R.id.recyclerViewHistory);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 2);
        recyclerViewHistory.setLayoutManager(layoutManager);
        recyclerViewHistory.setHasFixedSize(true);
        imageNull = findViewById(R.id.imageNull);
        btnBack = findViewById(R.id.btnBack);
    }

    @Override
    public void getListDataHistorySuccess(List<History> historyList) {
        recyclerViewHistory.setVisibility(View.VISIBLE);
        imageNull.setVisibility(View.GONE);
        historyAdapter = new HistoryAdapter(context, historyList, new ItemProductClickListener() {
            @Override
            public void onClickItemProduct(Product product) {
                History history = HistoryDatabase.getInstance(getApplicationContext()).historyDAO().getHistoryProduct(product.getProduct_id(), Constant.CUSTOMER_ID);
                if(history == null){
                    History historyNew = new History(product.getProduct_id(), Constant.CUSTOMER_ID, product.getCategory_id(), product.getCategory_name()
                            , product.getProduct_name(), product.getProduct_desc(), product.getProduct_price(), product.getProduct_image());
                    HistoryDatabase.getInstance(getApplicationContext()).historyDAO().insertHistoryProduct(historyNew);
                }
                Intent intent = new Intent(getApplicationContext() , ProductDetailsActivity.class);
                intent.putExtra("product" , product);
                startActivity(intent);
            }
        });
        recyclerViewHistory.setAdapter(historyAdapter);
    }

    @Override
    public void getListDataHistoryNull() {
        imageNull.setVisibility(View.VISIBLE);
        recyclerViewHistory.setVisibility(View.GONE);
    }
}