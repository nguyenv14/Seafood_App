package com.nhanlovecode.doancuoiky.Views.ProductCategory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.nhanlovecode.doancuoiky.Adapters.ProductAdapter;
import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseLocal.RoomDatabase.HistoryDatabase;
import com.nhanlovecode.doancuoiky.EventClickListener.ItemProductClickListener;
import com.nhanlovecode.doancuoiky.Models.History;
import com.nhanlovecode.doancuoiky.Models.Product;
import com.nhanlovecode.doancuoiky.R;
import com.nhanlovecode.doancuoiky.Views.ProductDetails.ProductDetailsActivity;

import java.util.List;

public class ProductCategoryActivity extends AppCompatActivity implements ProductCategoryMVPView{

    Toolbar toolBar;
    RecyclerView rcProductByCategory;
    
    ProductAdapter mProductAdapter;
    ProductCategoryPresenter mProductCategoryPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_category);
        mProductCategoryPresenter = new ProductCategoryPresenter(this,this);
        initUI();
        eventClick();
        getData();

    }

    private void getData() {
        Intent intent = getIntent();
        mProductCategoryPresenter.getCategoryID(intent);
    }

    private void eventClick() {
        eventClickToolBar();
    }

    private void eventClickToolBar() {
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initUI() {
        toolBar = findViewById(R.id.toolBar);
        rcProductByCategory = findViewById(R.id.rcProductByCategory);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this , 2);
        rcProductByCategory.setLayoutManager(gridLayoutManager);
    }
    @Override
    public void getCategoryIdSuccess(int category_id) {
        mProductCategoryPresenter.getProductByCategory(category_id);
    }
    
    @Override
    public void getCategoryIdError() {
        Toast.makeText(this, "Bug", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getDataProductByCategoryError() {
      
    }

    @Override
    public void getDataProductByCategorySuccess(List<Product> productList) {
        mProductAdapter = new ProductAdapter(this, productList, new ItemProductClickListener() {
            @Override
            public void onClickItemProduct(Product product) {
                gotoProductDetails(product);
            }
        });
        rcProductByCategory.setAdapter(mProductAdapter);
    }

    private void gotoProductDetails(Product product){
        History history = HistoryDatabase.getInstance(this).historyDAO().getHistoryProduct(product.getProduct_id(), Constant.CUSTOMER_ID);
        if(history == null){
            History historyNew = new History(product.getProduct_id(), Constant.CUSTOMER_ID, product.getCategory_id(), product.getCategory_name()
                    , product.getProduct_name(), product.getProduct_desc(), product.getProduct_price(), product.getProduct_image());
            HistoryDatabase.getInstance(this).historyDAO().insertHistoryProduct(historyNew);
        }
        Intent intent = new Intent(this , ProductDetailsActivity.class);
        intent.putExtra("product" , product);
        startActivity(intent);
    }
}