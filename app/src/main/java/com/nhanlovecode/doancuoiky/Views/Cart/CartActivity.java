package com.nhanlovecode.doancuoiky.Views.Cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nhanlovecode.doancuoiky.Adapters.CartAdapter;
import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseLocal.RoomDatabase.CartDatabase;
import com.nhanlovecode.doancuoiky.DatabaseLocal.SharedPreferences.MySharedPreferencesManager;
import com.nhanlovecode.doancuoiky.EventClickListener.ItemCartClickListener;
import com.nhanlovecode.doancuoiky.Models.Cart;
import com.nhanlovecode.doancuoiky.R;
import com.nhanlovecode.doancuoiky.Views.CheckOut.CheckOutActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements CartMVPView {

    Toolbar toolBar;

    SwipeRefreshLayout swipeRefreshLayout;

    TextView btnBuyNow;

    CheckBox cbCheckAll;

    RecyclerView rcCart ;
    TextView txtTotalPrice;

    CartAdapter mCartAdapter;
    CartPresenter mCartPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mCartPresenter = new CartPresenter(this , this);
        initUI();
        eventClick();
        getData();
        reLoad();
    }

    @Override
    protected void onResume() {
        super.onResume();
        reLoad();
    }

    private void reLoad() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadCart();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getData() {
        mCartPresenter.getDataCart();
    }

    private void loadCart(){
        mCartPresenter.loadCart();
    }

    private void eventClick() {
        eventClickCheckBox();
        eventClickBuyNow();
        eventClickToolbar();
    }

    private void eventClickToolbar() {
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }); //
    }

    private void eventClickBuyNow() {
        btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this , CheckOutActivity.class);

                Gson gson = new Gson();
                Type type = new TypeToken<List<Cart>>(){}.getType();
                String strCartListChecked =  gson.toJson(Constant.mCartListChecked, type);
                intent.putExtra("strCartListChecked" , strCartListChecked);

                startActivity(intent);

            }
        });
    }
    private void eventClickCheckBox() {
        cbCheckAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mCartPresenter.checkedAll();
                }else {
                    mCartPresenter.unCheckedAll();
                }
            }
        });
    }


    private void initUI() {
        cbCheckAll = findViewById(R.id.cbCheckAll);
        rcCart = findViewById(R.id.rcCart);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcCart.setLayoutManager(layoutManager);
        rcCart.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rcCart.getContext(), LinearLayoutManager.VERTICAL);
        rcCart.addItemDecoration(dividerItemDecoration);
        btnBuyNow  = findViewById(R.id.btnBuyNow);
        txtTotalPrice = findViewById(R.id.txtTotalPrice);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        toolBar = findViewById(R.id.toolBar);
        toolBar.setTitle("Giỏ Hàng");
        toolBar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        stateButton();
    }

    private void stateButton(){
        if(Constant.mCartListChecked.isEmpty()){
            btnBuyNow.setClickable(false);
            btnBuyNow.setBackgroundResource(R.drawable.bg_btn_buynow_red);
        }else {
            btnBuyNow.setClickable(true);
            btnBuyNow.setBackgroundResource(R.drawable.bg_btn_buynow);
        }
    }

    private void stateCheckBoxAll(){
        mCartPresenter.getStateCheckBoxAll(Constant.mCartListChecked);
    }

    public void calTotalAmount(){
        mCartPresenter.calTotalAmount(Constant.mCartListChecked);
    };

    @Override
    public void getDataCartSuccess(List<Cart> cartList) {
        mCartAdapter = new CartAdapter(this, cartList, Constant.mCartListChecked ,new ItemCartClickListener() {
            @Override
            public void onClickDeleteItemCart(Cart cart, Boolean isChecked) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                builder.setTitle("Xóa Sản Phẩm Khỏi Giỏ Hàng");
                builder.setMessage("Bạn có muốn xóa " + cart.getProduct_name() + " ra khỏi giỏ hàng");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mCartPresenter.deleteItemCart(cart);
                        if(isChecked){
                            mCartPresenter.deleteItemCartChecked(Constant.mCartListChecked,cart);
                        }
                    }
                });
                builder.setNegativeButton("Không", null);
                builder.show();
            }

            @Override
            public void onClickPlusItemCart(Cart cart, Boolean isChecked) {
                mCartPresenter.plusItemCart(cart);
                if(isChecked){
                    mCartPresenter.plusItemCartChecked(Constant.mCartListChecked,cart);
                }
            }

            @Override
            public void onClickMinusItemCart(Cart cart, Boolean isChecked) {
                mCartPresenter.minusItemCart(cart);
                if(isChecked){
                    mCartPresenter.minusItemCartChecked(Constant.mCartListChecked,cart);
                }
            }

            @Override
            public void onClickCItemIsCheckedCart(Cart cart) {
                Constant.mCartListChecked.add(cart);
                calTotalAmount();
                stateButton();
                stateCheckBoxAll();

            }

            @Override
            public void onClickCItemNoCheckedCart(Cart cart) {
                Constant.mCartListChecked.remove(cart);
                calTotalAmount();
                stateButton();
            }
        });
        rcCart.setAdapter(mCartAdapter);
    }

    @Override
    public void getDataCartError() {

    }

    @Override
    public void loadDataCartSuccess(List<Cart> cartList) {
        mCartAdapter.setData(cartList , Constant.mCartListChecked);
        rcCart.setAdapter(mCartAdapter);
    }

    @Override
    public void deleteItemCartSuccess() {
        mCartPresenter.loadCart();
        calTotalAmount();
        stateButton();
    }

    @Override
    public void minusItemCartSuccess() {
        loadCart();
    }

    @Override
    public void minusItemCartError() {

    }

    @Override
    public void plusItemCartSuccess() {
        loadCart();
    }

    @Override
    public void plusItemCartError() {

    }

    @Override
    public void checkedAllSuccess(List<Cart> cartList) {
        Constant.mCartListChecked.clear();
        Constant.mCartListChecked = cartList;
        loadCart();
        calTotalAmount();
        stateButton();
    }

    @Override
    public void unCheckedAllSuccess() {
        Constant.mCartListChecked.clear();
        loadCart();
        calTotalAmount();
        stateButton();
    }

    @Override
    public void calTotalAmountSuccess(Double totalAmount) {
        txtTotalPrice.setText(cutNumber(Double.valueOf(totalAmount).intValue()));
    }

    @Override
    public void minusItemCartCheckedSuccess(List<Cart> cartListChecked) {
        Constant.mCartListChecked = cartListChecked;
        calTotalAmount();
    }

    @Override
    public void plusItemCartCheckedSuccess(List<Cart> cartListChecked) {
        Constant.mCartListChecked = cartListChecked;
        calTotalAmount();
    }

    @Override
    public void stateCheckBoxFull() {
        cbCheckAll.setChecked(true);
    }

    @Override
    public void stateCheckBoxNotFull() {
        cbCheckAll.setChecked(false);
    }

    @Override
    public void deleteItemCartCheckedSuccess(List<Cart> cartListChecked) {
        Constant.mCartListChecked = cartListChecked;
        calTotalAmount();
        stateButton();
        loadCart();
    }

    public String cutNumber(int num) {
        if (num >= 1000000) {
            String strNum = Integer.toString(num);
            return strNum.substring(0, strNum.length() - 3) + "k";
        } else {
            return num + "đ";
        }
    }
}