package com.nhanlovecode.doancuoiky.Views.Order.OrderDetails;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhanlovecode.doancuoiky.Adapters.OrderDetailsAdapter;
import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.Models.Comment;
import com.nhanlovecode.doancuoiky.Models.Order;
import com.nhanlovecode.doancuoiky.Models.OrderDetails;
import com.nhanlovecode.doancuoiky.R;
import com.nhanlovecode.doancuoiky.Views.Cart.CartActivity;
import com.nhanlovecode.doancuoiky.Views.LoginAndSignup.Login.Evaluate.EvaluateActivity;

import java.text.DecimalFormat;
import java.util.List;

public class OrderDetailsActivity extends AppCompatActivity implements OrderDetailsMVPView {
    Toolbar toolbar;
    Context context;
    ImageView imgCartToolbar;
    RecyclerView rcOrderDetails;
    TextView order_code, order_status, order_date, shipping_name, shipping_phone, shipping_address, shipping_notes;

    TextView product_fee, product_coupon, total_price, pricePayment, tvStatus, evaluate_status;

    LinearLayout btnStatus;
    CardView formEvaluate;

    Order order;
    TextView customer_name, evaluate_date, evaluate_content, evaluate_star_quantity;
    OrderDetailsPresenter mOrderDetailsPresenter;
    OrderDetailsAdapter mOrderDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        mOrderDetailsPresenter = new OrderDetailsPresenter(this , this);
        initUI();
        eventClick();
        getData();
    }

    private void getData() {
        Intent intent = getIntent();
        mOrderDetailsPresenter.getDataOrder(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    private void eventClick() {
        btnStatus.setOnClickListener(view -> {
            if(tvStatus.getText().equals("Đánh giá sản phẩm")){
                goToEvaluateActivity();
            }else if(tvStatus.getText().equals("Hủy đơn hàng")){
                eventCancelOrder();
            }else if(tvStatus.getText().equals("Đã nhận được hàng")){
                eventConfirmOrder();
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgCartToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderDetailsActivity.this , CartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void eventConfirmOrder() {
        AlertDialog.Builder buider = new AlertDialog.Builder(context).setTitle("Check đơn hàng")
                .setMessage("Bạn đã nhận được hàng?")
                .setPositiveButton("Có", (dialogInterface, i) -> {
                    mOrderDetailsPresenter.receiveOrder(order.getOrder_code());
                }).setNegativeButton("Hủy", null);
        AlertDialog alertDialog = buider.create();
        alertDialog.show();
    }

    private void eventCancelOrder() {
        AlertDialog.Builder buider = new AlertDialog.Builder(context).setTitle("Hủy đơn hàng")
                .setMessage("Bạn có chắc chắn hủy đơn hàng?")
                .setPositiveButton("Có", (dialogInterface, i) -> {
                    mOrderDetailsPresenter.cancelOrder(order.getOrder_code());
                }).setNegativeButton("Hủy", null);
        AlertDialog alertDialog = buider.create();
        alertDialog.show();
    }

    private void goToEvaluateActivity() {
        Intent intent = new Intent(this, EvaluateActivity.class);
        intent.putExtra("order", order);
        startActivityForResult(intent, Constant.KEY_UPDATE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Constant.KEY_UPDATE && resultCode == Activity.RESULT_OK){
            mOrderDetailsPresenter.getOrderDetails(order.getOrder_code());
        }
    }

    private void initUI() {
        toolbar = findViewById(R.id.toolbar);
        imgCartToolbar = findViewById(R.id.imgCartToolbar);
        order_code = findViewById(R.id.order_code);
        order_status = findViewById(R.id.order_status);
        order_date = findViewById(R.id.order_date);
        shipping_name = findViewById(R.id.shipping_name);
        shipping_phone = findViewById(R.id.shipping_phone);
        shipping_address = findViewById(R.id.shipping_address);
        shipping_notes = findViewById(R.id.shipping_notes);
        rcOrderDetails = findViewById(R.id.rcOrderDetails);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcOrderDetails.setLayoutManager(layoutManager);
        rcOrderDetails.setHasFixedSize(true);
        total_price = findViewById(R.id.total_price);
        pricePayment = findViewById(R.id.pricePayment);
        product_coupon = findViewById(R.id.product_coupon);
        product_fee = findViewById(R.id.product_fee);
        btnStatus = findViewById(R.id.btnStatus);
        tvStatus = findViewById(R.id.tvStatus);
        formEvaluate = findViewById(R.id.formEvaluate);
        btnStatus = findViewById(R.id.btnStatus);
        customer_name = findViewById(R.id.customer_name);
        evaluate_date = findViewById(R.id.evaluate_date);
        evaluate_content = findViewById(R.id.evaluate_content);
        evaluate_star_quantity = findViewById(R.id.evaluate_star_quantity);
        evaluate_status = findViewById(R.id.evaluate_status);
    }

    @Override
    public void getDataOrderSuccess(Order order) {
        putDataProduct(order.getOrderDetails());
        putDataTotalOrder(order);
        mOrderDetailsPresenter.getEvaluate(order.getOrder_code());
    }

    private void putDataTotalOrder(Order order) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        order_code.setText("Mã đơn hàng: "+ order.getOrder_code());
        order_date.setText("Thời gian đặt: " + order.getOrder_date());
//        order_status.setText();
        if(order.getOrder_status() == -1){
            order_status.setText("Tình trạng: Đơn hàng từ chối");
            btnStatus.setVisibility(View.GONE);
            formEvaluate.setVisibility(View.GONE);
        }else if(order.getOrder_status() == 0){
            order_status.setText("Tình trạng: Đơn hàng đang chờ xác nhận");
            btnStatus.setVisibility(View.VISIBLE);
            btnStatus.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
            tvStatus.setText("Hủy đơn hàng");
            formEvaluate.setVisibility(View.GONE);
        }else if(order.getOrder_status() == 1){
            order_status.setText("Tình trạng: Đơn hàng đang được giao");
            btnStatus.setVisibility(View.VISIBLE);
            btnStatus.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.orange)));
            tvStatus.setText("Đã nhận được hàng");
            formEvaluate.setVisibility(View.GONE);
        }else if(order.getOrder_status() == 2){
            order_status.setText("Tình trạng: Đơn hàng đã nhận, chưa đánh giá");
            btnStatus.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.ucrop_color_blaze_orange)));
            tvStatus.setText("Đánh giá sản phẩm");
            formEvaluate.setVisibility(View.GONE);
        }else if(order.getOrder_status() == 4){
            order_status.setText("Tình trạng: Đơn hàng đã nhận, đã đánh giá");
            btnStatus.setVisibility(View.GONE);
        }else if(order.getOrder_status() == 3){
            order_status.setText("Tình trạng: Đơn hàng bị từ chối nhận bởi khách hàng");
            btnStatus.setVisibility(View.GONE);
            formEvaluate.setVisibility(View.GONE);
        }
        OrderDetailsAdapter orderDetailsAdapter = new OrderDetailsAdapter(this, order.getOrderDetails());
        rcOrderDetails.setAdapter(orderDetailsAdapter);
        total_price.setText(decimalFormat.format(order.getTotal_price()) + " đ");
        product_fee.setText("+ " + decimalFormat.format(order.getProduct_fee()) + " đ");
        product_coupon.setText("( " + order.getProduct_coupon() + " ) - "+decimalFormat.format(order.getProduct_price_coupon()) + " đ ");
        pricePayment.setText(decimalFormat.format(order.getTotal_price() + order.getProduct_fee() + order.getProduct_price_coupon()) + " đ");
    }

    private void putDataProduct(List<OrderDetails> orderDetails) {
        mOrderDetailsAdapter = new OrderDetailsAdapter(this , orderDetails);
        rcOrderDetails.setAdapter(mOrderDetailsAdapter);
    }

    @Override
    public void getDataOrderErorr() {
        Toast.makeText(this, "Có Lỗi Xảy Ra Ở Phần Chi Tiết Đơn Hàng", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void getEvaluateOrderSuccess(Comment comment) {
        formEvaluate.setVisibility(View.VISIBLE);
        evaluate_status.setVisibility(View.GONE);
        customer_name.setText(comment.getComment_customer_name());
        evaluate_date.setText(comment.getComment_date());
        evaluate_content.setText(comment.getComment_content());
        evaluate_star_quantity.setText(comment.getComment_rate_star() + "");
    }

    @Override
    public void getEvaluateOrderNull() {
        evaluate_status.setVisibility(View.VISIBLE);
        formEvaluate.setVisibility(View.GONE);
    }
}