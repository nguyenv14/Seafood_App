package com.nhanlovecode.doancuoiky.Views.CheckOut;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nhanlovecode.doancuoiky.Adapters.CheckOutAdapter;
import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.Models.Cart;
import com.nhanlovecode.doancuoiky.Models.Coupon;
import com.nhanlovecode.doancuoiky.Models.DressPersonal;
import com.nhanlovecode.doancuoiky.Models.Order;
import com.nhanlovecode.doancuoiky.R;
import com.nhanlovecode.doancuoiky.Views.DressPersonal.AddDressPersonal.AddDressPersonalActivity;
import com.nhanlovecode.doancuoiky.Views.DressPersonal.ChooseDressPersonal.ChooseDressPersonalActivity;
import com.nhanlovecode.doancuoiky.Views.Receipt.ReceiptActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import vn.momo.momo_partner.AppMoMoLib;

public class CheckOutActivity extends AppCompatActivity implements CheckOutMVPView{
    LinearLayout loAddressPersonal , loAddAddressPersonal , loCoupon , loInfoCoupon , loCouponPriceSale , loPayment;
    TextView txtAddDress , tvNameAddress , tvNameCustomer , tvAddressCustomer;
    Toolbar toolBar;
    RecyclerView rcViewCheckOut;
    List<Cart> cartList;
    CheckOutAdapter mCheckOutAdapter;
    CheckOutPresenter mCheckOutPresenter;
    Dialog dialog;
    EditText editCouponCode ;
    LinearLayout btnCancelDialog , btnConfirm;
    TextView tvNameCoupon , tvCouponNameCode , tvCouponPriceSale ;
    Coupon mCoupon;
    List<Cart> mCartList;
    ImageView imgRemoveCoupon;
    TextView txtTotalPrice , txtDeliveringFee , txtCouponPriceSale , txtPricePayment , txtPayment ;
    TextView btnOrder;
    Double mTotalPrice, mDeliveringFee , mCouponPriceSale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.DEVELOPMENT);  // AppMoMoLib.ENVIRONMENT.PRODUCTION
        mCheckOutPresenter = new CheckOutPresenter(this , this ,  AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.DEVELOPMENT),getCurrentActivity());
        initUI();
        eventClick();
        getData();
        calculatingTotalProgress();
    }

    public Activity getCurrentActivity() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
        calculatingTotalProgress();
    }

    private void getData() {
        getDataListCart();
        getDataAddressPersonal();
    }

    private void getDataAddressPersonal() {
        mCheckOutPresenter.getDataAddressPersonal();
    }

    private void getDataListCart() {
        String json = getIntent().getStringExtra("strCartListChecked");
        Gson gson = new Gson();
        Type type = new TypeToken<List<Cart>>(){}.getType();
        cartList = gson.fromJson(json, type);
        mCartList = cartList;
        mCheckOutAdapter = new CheckOutAdapter(getApplicationContext() , cartList);
        rcViewCheckOut.setAdapter(mCheckOutAdapter);
    }

    private void eventClick() {
        eventClickToolBar();
        eventClickAddAddress();
        eventClickChooseAddress();
        eventClickCoupon();
        eventClickPayment();
        eventClickOrder();
    }

    private void eventClickChooseAddress() {
        loAddressPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckOutActivity.this , ChooseDressPersonalActivity.class);
                startActivity(intent);
            }
        });
    }

    private void eventClickOrder() {
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder buider = new AlertDialog.Builder(CheckOutActivity.this).setTitle("Xác nhận")
                            .setMessage("Bạn muốn đặt đơn hàng này?")
                            .setPositiveButton("Có", (dialogInterface, i) -> {
                                String Payment = txtPayment.getText().toString();
                                mCheckOutPresenter.putOrder(mTotalPrice, mDeliveringFee,mCouponPriceSale ,Payment , mCartList , mCoupon , Constant.mDressPersonal);
                            }).setNegativeButton("Không", null);
                    AlertDialog alertDialog = buider.create();
                alertDialog.show();
            }
        });

    }

    private void eventClickPayment() {
        loPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initUIBottomSheetDialogPayment();
            }
        });
    }

    private void initUIBottomSheetDialogPayment(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_payment_layout);

        RadioButton radio_direct = bottomSheetDialog.findViewById(R.id.radio_direct);
        RadioButton radio_momo = bottomSheetDialog.findViewById(R.id.radio_momo);
        TextView btnConfirm = bottomSheetDialog.findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (radio_direct.isChecked()){
                   txtPayment.setText(radio_direct.getText().toString());
               }else if (radio_momo.isChecked()){
                   txtPayment.setText(radio_momo.getText().toString());
               }
               bottomSheetDialog.dismiss();
            }
        });
        TextView btnCancel = bottomSheetDialog.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.show();
    }

    private void eventClickCoupon() {
        loCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initUIDialogCoupon();
            }
        });
        loInfoCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initUIDialogCoupon();
            }
        });
        imgRemoveCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCoupon = null;
                loCoupon.setVisibility(View.VISIBLE);
                loInfoCoupon.setVisibility(View.GONE);
                calculatingTotalProgress();
            }
        });
    }

    private void eventClickAddAddress() {
        loAddAddressPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckOutActivity.this , AddDressPersonalActivity.class);
                startActivity(intent);
            }
        });
    }

    private void eventClickToolBar() {
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    private void eventClickConfirmDiaLogCoupon() {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String coupon_name_code = editCouponCode.getText().toString();
                mCheckOutPresenter.checkCoupon(coupon_name_code);
            }
        });
    }

    private void eventClickCancelDiaLogCoupon() {
        btnCancelDialog.setOnClickListener(view -> {
            dialog.dismiss();
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void getDataAddressPersonalSuccess(DressPersonal dressPersonal) {
        Constant.mDressPersonal = dressPersonal;
        loAddressPersonal.setVisibility(View.VISIBLE);
        tvNameAddress.setText(dressPersonal.getName_dress());
        tvNameCustomer.setText(dressPersonal.getShipping_name() + " (" + dressPersonal.getShipping_phone() + ")");
        tvAddressCustomer.setText(dressPersonal.getHome_number() + ", " + dressPersonal.getWard_name() + ", " +dressPersonal.getProvince_name() + ", " + dressPersonal.getCity_name());

    }

    @Override
    public void getDataAddressPersonalError() {
        loAddAddressPersonal.setVisibility(View.VISIBLE);
    }

    @Override
    public void checkCouponError() {
        Toast.makeText(this, "Mã Giảm Giá Không Tồn Tại Hoặc Hết Hạn", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void checkCouponSuccess(Coupon coupon) {
        mCoupon = coupon;
        tvNameCoupon.setText(coupon.getCoupon_name());
        tvCouponNameCode.setText(coupon.getCoupon_name_code());
        if (coupon.getCoupon_condition() == 1){
            tvCouponPriceSale.setText(coupon.getCoupon_price_sale() +"%");
        }else {
            tvCouponPriceSale.setText(coupon.getCoupon_price_sale() +"đ");
        }
        loInfoCoupon.setVisibility(View.VISIBLE);
        loCoupon.setVisibility(View.GONE);
        dialog.dismiss();
        calculatingTotalProgress();
    }


    @Override
    public void calculatingTotalProgressSuccess(Double totalPrice, Double deliveringFee, Double couponPriceSale, Double pricePayment) {
        this.mTotalPrice = totalPrice;
        this.mDeliveringFee =deliveringFee;
        this.mCouponPriceSale = couponPriceSale;
        txtTotalPrice.setText(formatPrice(totalPrice));
        txtDeliveringFee.setText("+ " +formatPrice(deliveringFee));
        if (couponPriceSale == 0.0){
            loCouponPriceSale.setVisibility(View.GONE);
        }else {
            loCouponPriceSale.setVisibility(View.VISIBLE);
            txtCouponPriceSale.setText("- "+ formatPrice(couponPriceSale));
        }
        txtPricePayment.setText(formatPrice(pricePayment));
    }
    public static String formatPrice(Double price) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        return decimalFormat.format(price);
    }
    @Override
    public void putOrderError(int typeError) {
        if (typeError == Constant.KEY_TYPE_ERROR_PAYMENT){
            Toast.makeText(this, "Vui Lòng Chọn Phương Thức Thanh Toán", Toast.LENGTH_SHORT).show();
        } else if (typeError == Constant.KEY_TYPE_ERROR_SHIPPING){
            Toast.makeText(this, "Vui Lòng Chọn Thông Tin Người Nhận Hàng", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void putOrderSuccess(Order order) {
        if (order.getPayment().getPayment_method() == 4) {
            Toast.makeText(this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();

        }else if (order.getPayment().getPayment_method() == 1) {
            Toast.makeText(this, "Thanh toán momo thành công !", Toast.LENGTH_SHORT).show();
        }
        mCheckOutPresenter.resetData(mCartList, order);
    }
    @Override
    public void resetDataSuccess (Order order){
        Gson gson = new Gson();
        String strOrder = gson.toJson(order);
        Intent intent = new Intent(CheckOutActivity.this, ReceiptActivity.class);
        intent.putExtra("strOrder", strOrder);
        startActivity(intent);
    }

    private void calculatingTotalProgress () {
        mCheckOutPresenter.calculatingTotalProgress(mCartList, Constant.mDressPersonal, mCoupon);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppMoMoLib.getInstance().REQUEST_CODE_MOMO && resultCode == -1) {
            if (data != null) {
                if (data.getIntExtra("status", -1) == 0) {
                    //TOKEN IS AVAILABLE
                    String token = data.getStringExtra("data"); //Token response
                    String phoneNumber = data.getStringExtra("phonenumber");
                    String env = data.getStringExtra("env");

                    if (env == null) {
                        env = "app";
                    }

                    if (token != null && !token.equals("")) {
//                        Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
                        // TODO: send phoneNumber & token to your server side to process payment with MoMo server
                        // IF Momo topup success, continue to process your order
                        mCheckOutPresenter.putOrderMomo(mTotalPrice, mDeliveringFee,mCouponPriceSale, mCartList , mCoupon , Constant.mDressPersonal);
                    } else {
                        Toast.makeText(this, "Đặt hàng không thành công 4!", Toast.LENGTH_SHORT).show();
//                        tvMessage.setText("message: " + this.getString(R.string.not_receive_info));
                    }
                } else if (data.getIntExtra("status", -1) == 1) {
                    Toast.makeText(this, "Đặt hàng không thành công 2!", Toast.LENGTH_SHORT).show();
                    String message = data.getStringExtra("message") != null ? data.getStringExtra("message") : "Thất bại";

                } else if (data.getIntExtra("status", -1) == 2) {
                    //TOKEN FAIL
                    Toast.makeText(this, "Đặt hàng không thành công 5!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "Đặt hàng bằng ví Momo không thành công!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Đặt hàng không thành công 7!", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == 1000 && resultCode == Activity.RESULT_OK) {

        }else{
            Toast.makeText(this, "Đặt hàng không thành công 1!", Toast.LENGTH_SHORT).show();
        }

        if(resultCode == RESULT_OK){
            getData();
            calculatingTotalProgress();
        }

    }

    private void initUI() {
        rcViewCheckOut = findViewById(R.id.rcViewCheckOut);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcViewCheckOut.setLayoutManager(layoutManager);
        rcViewCheckOut.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rcViewCheckOut.getContext(), LinearLayoutManager.VERTICAL);
        rcViewCheckOut.addItemDecoration(dividerItemDecoration);
        toolBar = findViewById(R.id.toolBar);
        txtAddDress = findViewById(R.id.txtAddDress);
        loAddressPersonal = findViewById(R.id.loAddressPersonal);
        loAddAddressPersonal = findViewById(R.id.loAddAddressPersonal);
        tvNameAddress = findViewById(R.id.tvNameAddress);
        tvNameCustomer = findViewById(R.id.tvNameCustomer);
        tvAddressCustomer = findViewById(R.id.tvAddressCustomer);
        loCoupon = findViewById(R.id.loCoupon);
        loInfoCoupon = findViewById(R.id.loInfoCoupon);
        tvNameCoupon = findViewById(R.id.tvNameCoupon);
        tvCouponNameCode = findViewById(R.id.tvCouponNameCode);
        tvCouponPriceSale = findViewById(R.id.tvCouponPriceSale);
        txtTotalPrice = findViewById(R.id.txtTotalPrice);
        txtDeliveringFee = findViewById(R.id.txtDeliveringFee);
        txtCouponPriceSale = findViewById(R.id.txtCouponPriceSale);
        txtPricePayment = findViewById(R.id.txtPricePayment);
        loCouponPriceSale = findViewById(R.id.loCouponPriceSale);
        imgRemoveCoupon = findViewById(R.id.imgRemoveCoupon);
        loPayment = findViewById(R.id.loPayment);
        txtPayment = findViewById(R.id.txtPayment);
        btnOrder  = findViewById(R.id.btnOrder);
    }

    private void initUIDialogCoupon(){
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_coupon_custom);

        Window window = dialog.getWindow();
        if (window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttribute = window.getAttributes();
        windowAttribute.gravity = Gravity.CENTER;
        window.setAttributes(windowAttribute);
        dialog.setCancelable(false);
        dialog.show();
        editCouponCode = dialog.findViewById(R.id.editCouponCode);
        btnCancelDialog = dialog.findViewById(R.id.btnCancelDialog);
        btnConfirm = dialog.findViewById(R.id.btnConfirm);
        eventClickCancelDiaLogCoupon();
        eventClickConfirmDiaLogCoupon();

    }
}