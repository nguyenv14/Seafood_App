package com.nhanlovecode.doancuoiky.Views.Receipt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.nhanlovecode.doancuoiky.Adapters.CategoryGroupAdapter;
import com.nhanlovecode.doancuoiky.Adapters.OrderDetailsAdapter;
import com.nhanlovecode.doancuoiky.Adapters.ProductAdapter;
import com.nhanlovecode.doancuoiky.AdaptersHelper.CategoryGroupAdapterHelper.CategoryGroupAdapterHelper;
import com.nhanlovecode.doancuoiky.AdaptersHelper.CategoryGroupAdapterHelper.ICategoryGroupAdapterHelper;
import com.nhanlovecode.doancuoiky.EventClickListener.ItemProductClickListener;
import com.nhanlovecode.doancuoiky.Models.Order;
import com.nhanlovecode.doancuoiky.Models.Product;
import com.nhanlovecode.doancuoiky.Models.Shipping;
import com.nhanlovecode.doancuoiky.R;
import com.nhanlovecode.doancuoiky.Views.Main.MainActivity;
import com.nhanlovecode.doancuoiky.Views.ProductDetails.ProductDetailsActivity;

import java.text.DecimalFormat;
import java.util.List;

public class ReceiptActivity extends AppCompatActivity {
    Context context;
    RecyclerView rcOrderDetails;
    TextView order_code, order_date, shipping_name;
    TextView payment_status;
    TextView pricePayment, tvStatus;

    LinearLayout btnBackHome;
    Order order;
    OrderDetailsAdapter mOrderDetailsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);
        context = this;
        initUI();
        eventClick();
        getData();

    }

    private void getData() {
        Intent intent = getIntent();
        String strOrder = intent.getStringExtra("strOrder");
        if (strOrder != null) {
            Gson gson = new Gson();
            order = gson.fromJson(strOrder, Order.class);
        }
        putDataTotalOrder(order);
    }
    private void putDataTotalOrder(Order order) {
        Shipping shipping = order.getShipping();
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        order_code.setText(order.getOrder_code());
        order_date.setText(order.getOrder_date());
        if(order.getPayment().getPayment_method() == 4){
            payment_status.setText("Thanh toán khi nhận hàng");
        }else{
            payment_status.setText("Đã thanh toán bằng ví MoMo");
        }
        shipping_name.setText(shipping.getShipping_name());
        pricePayment.setText(decimalFormat.format(order.getTotal_price()) + " đ");
    }

    private void eventClick() {
        eventClickBackHome();
//        autoGoBackHome();
    }

    private void autoGoBackHome() {
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run(){
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
        },10000);
    }

    private void eventClickBackHome() {
        btnBackHome.setOnClickListener(view -> {
            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }

    private void initUI() {
        order_code = findViewById(R.id.order_code);
        order_date = findViewById(R.id.order_date);
        shipping_name = findViewById(R.id.shipping_name);
        payment_status = findViewById(R.id.payment_status);
        pricePayment = findViewById(R.id.pricePayment);
        btnBackHome = findViewById(R.id.btnBackHome);
        tvStatus = findViewById(R.id.tvStatus);
    }
}