package com.nhanlovecode.doancuoiky.Views.Search;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.slider.RangeSlider;
import com.nhanlovecode.doancuoiky.Adapters.ProductAdapter;
import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseLocal.RoomDatabase.HistoryDatabase;
import com.nhanlovecode.doancuoiky.EventClickListener.ItemProductClickListener;
import com.nhanlovecode.doancuoiky.Models.History;
import com.nhanlovecode.doancuoiky.Models.Product;
import com.nhanlovecode.doancuoiky.R;
import com.nhanlovecode.doancuoiky.Views.ProductDetails.ProductDetailsActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchViewActivity extends AppCompatActivity implements SearchActivityMVPView{
    Spinner spinnerCategory, spinnerFilter;
    SearchView searchView;
    ImageView btnVoice, btnToCart, btnCancel;

    ProductAdapter productAdapter;

    RecyclerView recyclerViewAllProduct;
    SearchActivityPresenter searchActivityPresenter;
    LottieAnimationView imageNull;
    String nameCategory;
    int numberFilter = 0;
    String nameSearch;
    List<String> arrayListCategory = new ArrayList<String>();
    List<String> arrayListFilter = new ArrayList<String>();

    RangeSlider rangePrice;

    TextView priceMin, priceMax;

    List<Float> listPrice = new ArrayList<Float>();

    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        searchActivityPresenter = new SearchActivityPresenter(this, this);
        intUI();
        getData();
        eventClick();
    }

    private void eventClick() {
        btnVoice.setOnClickListener(view -> {
            speakSearch();
        });

        btnCancel.setOnClickListener(view -> {
            finish();
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchActivityPresenter.searchProduct(query, nameCategory, numberFilter, listPrice.get(0), listPrice.get(1));
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                nameSearch = newText;
                searchActivityPresenter.searchProduct(newText, nameCategory, numberFilter, listPrice.get(0), listPrice.get(1));
                return false;
            }
        });

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nameCategory = arrayListCategory.get(i);
                if(nameSearch == null){
                    nameSearch = "";
                }
                try {
                    searchActivityPresenter.searchProduct(nameSearch, nameCategory, numberFilter, listPrice.get(0), listPrice.get(1));
                }catch (IndexOutOfBoundsException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(nameSearch == null){
                    nameSearch = "";
                }
                numberFilter = i;
                try {
                    searchActivityPresenter.searchProduct(nameSearch, nameCategory, numberFilter, listPrice.get(0), listPrice.get(1));
                }catch (IndexOutOfBoundsException e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        rangePrice.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                listPrice = rangePrice.getValues();
                priceMin.setText( decimalFormat.format(Math.round(listPrice.get(0))/1000) + "k đ");
                priceMax.setText( decimalFormat.format(Math.round(listPrice.get(1))/1000) + "k đ");
                if(nameSearch == null){
                    nameSearch = "";
                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        searchActivityPresenter.searchProduct(nameSearch, nameCategory, numberFilter, listPrice.get(0), listPrice.get(1));
                    }
                }, 1500);
            }
        });
    }

    private void speakSearch() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Nói sản phẩm bạn muốn tìm...");
        startActivityForResult(intent, Constant.REQUEST_VOICE_SPEAK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case Constant.REQUEST_VOICE_SPEAK:{
                if(resultCode == RESULT_OK && null!=data){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    searchView.setQuery(result.get(0), true);
                }
            }
        }
    }

    private void getData() {
        searchActivityPresenter.getDataNameSpinnerCategory();
        searchActivityPresenter.getDataProductToSearchView();
        searchActivityPresenter.getPriceMinPriceMax();
        arrayListFilter.add(0,"Sắp xếp");
        arrayListFilter.add("Giá giảm dần");
        arrayListFilter.add("Giá tăng dần");
        arrayListFilter.add("Từ A-Z");
        arrayListFilter.add("Từ Z-A");
        ArrayAdapter spinnerAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayListFilter);
        spinnerFilter.setAdapter(spinnerAdapter);
    }

    private void intUI() {
        searchView = findViewById(R.id.searchView);
        btnVoice = findViewById(R.id.btnVoice);
        btnCancel = findViewById(R.id.btnCancel);
        btnToCart = findViewById(R.id.btnCart);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        recyclerViewAllProduct = findViewById(R.id.recyclerViewAllProduct);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerViewAllProduct.setLayoutManager(layoutManager);
        recyclerViewAllProduct.setHasFixedSize(true);
        imageNull = findViewById(R.id.imageNull);
        spinnerFilter = findViewById(R.id.spinnerFilter);
        rangePrice = findViewById(R.id.rangePrice);
        priceMin = findViewById(R.id.priceMin);
        priceMax = findViewById(R.id.priceMax);
    }
    @Override
    public void getDataCategoryNameSuccess(List<String> stringList) {
        stringList.add(0, "Danh mục");
        arrayListCategory = stringList;
        ArrayAdapter spinnerAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, stringList);
        spinnerCategory.setAdapter(spinnerAdapter);
    }

    @Override
    public void getDataListAllProductSuccess(List<Product> productList) {
        recyclerViewAllProduct.setVisibility(View.VISIBLE);
        imageNull.setVisibility(View.GONE);
        productAdapter = new ProductAdapter(getApplicationContext(), productList, new ItemProductClickListener() {
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
        recyclerViewAllProduct.setAdapter(productAdapter);
    }

    @Override
    public void getDataListAllProductError() {
        recyclerViewAllProduct.setVisibility(View.GONE);
        imageNull.setVisibility(View.VISIBLE);
    }

    @Override
    public void getDataPriceMinAndPriceMaxSuccess(Product productMin, Product productMax) {
        float priceMin1 = productMin.getProduct_price().floatValue();
        float priceMax1 = productMax.getProduct_price().floatValue();
        rangePrice.setValueFrom(priceMin1);
        rangePrice.setValueTo(priceMax1);
        rangePrice.setStepSize(10000f);
        rangePrice.setValues(priceMin1, priceMax1);
        listPrice = rangePrice.getValues();
        priceMin.setText( decimalFormat.format(Math.round(listPrice.get(0))/1000) + "k đ");
        priceMax.setText( decimalFormat.format(Math.round(listPrice.get(1))/1000) + "k đ");
    }
}