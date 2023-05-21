package com.nhanlovecode.doancuoiky.Views.Similar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.nhanlovecode.doancuoiky.Adapters.ProductAdapter;
import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseLocal.RoomDatabase.HistoryDatabase;
import com.nhanlovecode.doancuoiky.EventClickListener.ItemProductClickListener;
import com.nhanlovecode.doancuoiky.Models.Favorite;
import com.nhanlovecode.doancuoiky.Models.History;
import com.nhanlovecode.doancuoiky.Models.Product;
import com.nhanlovecode.doancuoiky.R;
import com.nhanlovecode.doancuoiky.Views.Cart.CartActivity;
import com.nhanlovecode.doancuoiky.Views.ProductDetails.ProductDetailsActivity;

import java.util.List;

public class SimilarActivity extends AppCompatActivity implements SimilarMVPView{

    Toolbar toolbar;
    RecyclerView recSimilarView;
    TextView txtNameProduct;
    LottieAnimationView imageNull;
    SimilarPresenter mSimilarPresenter;
    ProductAdapter productAdapter;
    ImageView imgCartToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar);
        mSimilarPresenter = new SimilarPresenter(this, this);
        initUI();
        getData();
        eventClick();
    }

    private void eventClick() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgCartToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimilarActivity.this , CartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        mSimilarPresenter.getDataIntent(getIntent());
    }

    private void initUI() {
        recSimilarView = findViewById(R.id.recSimilarView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recSimilarView.setLayoutManager(layoutManager);
        recSimilarView.setHasFixedSize(true);
        txtNameProduct = findViewById(R.id.txtNameProduct);
        imageNull = findViewById(R.id.imageNull);
        toolbar = findViewById(R.id.toolbar);
        imgCartToolbar = findViewById(R.id.imgCartToolbar);
    }

    @Override
    public void getDataProductCategorySuccess(List<Product> productList) {
        recSimilarView.setVisibility(View.VISIBLE);
        imageNull.setVisibility(View.GONE);
        productAdapter = new ProductAdapter(SimilarActivity.this, productList, new ItemProductClickListener() {
            @Override
            public void onClickItemProduct(Product product) {
                History history = HistoryDatabase.getInstance(SimilarActivity.this).historyDAO().getHistoryProduct(product.getProduct_id(), Constant.CUSTOMER_ID);
                if(history == null){
                    History historyNew = new History(product.getProduct_id(), Constant.CUSTOMER_ID, product.getCategory_id(), product.getCategory_name()
                            , product.getProduct_name(), product.getProduct_desc(), product.getProduct_price(), product.getProduct_image());
                    HistoryDatabase.getInstance(SimilarActivity.this).historyDAO().insertHistoryProduct(historyNew);
                }
                Intent intent = new Intent(SimilarActivity.this , ProductDetailsActivity.class);
                intent.putExtra("product" , product);
                startActivity(intent);
            }
        });
        recSimilarView.setAdapter(productAdapter);
    }

    @Override
    public void getDataProductCategoryError() {
        recSimilarView.setVisibility(View.GONE);
        imageNull.setVisibility(View.VISIBLE);
    }

    @Override
    public void getDataIntentSuccess(int category_id, int product_id) {
        mSimilarPresenter.getDataCategoryProduct(category_id , product_id);
    }

    @Override
    public void getDataIntentError() {
        Toast.makeText(this, "Lỗi Khi Vào Trang Sản Phẩm Cùng Danh Mục", Toast.LENGTH_SHORT).show();
        finish();
    }
}