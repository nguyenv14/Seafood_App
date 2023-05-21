package com.nhanlovecode.doancuoiky.Views.DressPersonal.ChooseDressPersonal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.nhanlovecode.doancuoiky.Adapters.AddressPersonalAdapter;
import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.EventClickListener.ItemAddressClickListener;
import com.nhanlovecode.doancuoiky.Models.DressPersonal;
import com.nhanlovecode.doancuoiky.R;
import com.nhanlovecode.doancuoiky.Views.DressPersonal.ShowDressPersonal.ShowDressPersonalActivity;
import com.nhanlovecode.doancuoiky.Views.DressPersonal.ShowDressPersonal.ShowDressPersonalMVPView;
import com.nhanlovecode.doancuoiky.Views.DressPersonal.ShowDressPersonal.ShowDressPersonalPresenter;

import java.util.List;

public class ChooseDressPersonalActivity extends AppCompatActivity implements ShowDressPersonalMVPView {

    Toolbar toolbar;
    RecyclerView rcDress;
    AddressPersonalAdapter adapter;
    ShowDressPersonalPresenter mShowDressPersonalPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_dress_personal);
        mShowDressPersonalPresenter = new ShowDressPersonalPresenter(this , this);
        initUI();
        getData();
        eventClick();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            getData();
        }
    }

    private void eventClick() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getData() {
        mShowDressPersonalPresenter.getDataDressPersonal();
    }

    private void loadData(){
        mShowDressPersonalPresenter.loadDataDressPersonal();
    }

    private void initUI() {
        rcDress = findViewById(R.id.rcDress);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rcDress.setLayoutManager(layoutManager);
        rcDress.setHasFixedSize(true);
        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    public void getDataDressPersonalError() {
        rcDress.setVisibility(View.GONE);
    }

    @Override
    public void getDataDressPersonalSuccess(List<DressPersonal> dressPersonalList) {
        rcDress.setVisibility(View.VISIBLE);
        adapter = new AddressPersonalAdapter(this, dressPersonalList, new ItemAddressClickListener() {
            @Override
            public void setClickSwitchCompat(DressPersonal dressPersonal) {
                mShowDressPersonalPresenter.updateDataDressPersonal(dressPersonal);
            }

            @Override
            public void setClickItemAddress(DressPersonal dressPersonal) {
                Constant.mDressPersonal = dressPersonal;
                Toast.makeText(ChooseDressPersonalActivity.this, "Lựa Chọn Địa Chỉ Thành Công !", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }

            @Override
            public void setClickDeleteItemAddress(DressPersonal dressPersonal) {
                mShowDressPersonalPresenter.deleteDataDressPersonal(dressPersonal);
            }
        });
        rcDress.setAdapter(adapter);
    }

    @Override
    public void updateDataDressPersonalSuccess() {
        loadData();
    }

    @Override
    public void updateDataDressPersonalError() {
        Toast.makeText(this, "Phải Có 1 Địa Chỉ Được Đặt Làm Địa Chỉ Mặc Định", Toast.LENGTH_SHORT).show();
        loadData();
    }

    @Override
    public void loadDataDressPersonalSuccess(List<DressPersonal> dressPersonalList) {
        rcDress.setVisibility(View.VISIBLE);
        adapter.setData(dressPersonalList);
        rcDress.setAdapter(adapter);
    }

    @Override
    public void loadDataDressPersonalError() {
        rcDress.setVisibility(View.GONE);
    }

    @Override
    public void deleteDataDressPersonalSuccess(DressPersonal dressPersonal) {
        Toast.makeText(this, "Xóa Thành Công "+dressPersonal.getName_dress(), Toast.LENGTH_SHORT).show();
        loadData();
    }

}