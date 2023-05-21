package com.nhanlovecode.doancuoiky.Views.Home;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nhanlovecode.doancuoiky.Adapters.CategoryAdapter;
import com.nhanlovecode.doancuoiky.Adapters.ProductAdapter;
import com.nhanlovecode.doancuoiky.Adapters.SliderAdapter;
import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseLocal.RoomDatabase.HistoryDatabase;
import com.nhanlovecode.doancuoiky.DatabaseLocal.SharedPreferences.MySharedPreferencesManager;
import com.nhanlovecode.doancuoiky.EventClickListener.ItemCategoryClickListener;
import com.nhanlovecode.doancuoiky.EventClickListener.ItemProductClickListener;
import com.nhanlovecode.doancuoiky.Models.Category;
import com.nhanlovecode.doancuoiky.Models.History;
import com.nhanlovecode.doancuoiky.Models.Product;
import com.nhanlovecode.doancuoiky.Models.Slider;
import com.nhanlovecode.doancuoiky.R;
import com.nhanlovecode.doancuoiky.Views.ProductDetails.ProductDetailsActivity;
import com.nhanlovecode.doancuoiky.Views.Search.SearchViewActivity;
import com.nhanlovecode.doancuoiky.Views.Similar.SimilarActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import me.relex.circleindicator.CircleIndicator3;

public class HomePageFragment extends Fragment implements HomePageMVPView {

    private LinearLayout loSlider , loNewProduct , loOrderProduct , loTrendingProduct , loProduct , loCategory;
    private ProgressBar progressBarHome , progressBarSeeMore;
    private Context context;
    private View mView;
    private HomePagePresenter mHomePagePresenter;
    private SliderAdapter mSliderAdapter;
    private ViewPager2 viewPager2Slider;
    private CircleIndicator3 circleIndicator3;
    private List<Slider> mSliderList;
    private RecyclerView rcViewNewProduct , rcViewTrendingProduct , rcViewOrderProduct ,recyclerviewProduct , rcCategory;
    private ProductAdapter mProductAdapter;
    private CategoryAdapter mCategoryAdapter;
    private TextView txtSeeMore;
    private TextView tvNewProduct , txtOrderProduct , txtTrendingProduct , txtProduct , txtCategory;
    private List<Product> productListAhihi = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getActivity();
        mHomePagePresenter = new HomePagePresenter(context , this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_home_page, container, false);
        initUI(mView);
        eventClick();
        getData();

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void eventClick() {
        viewPager2Slider.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        autoRunSlider(mSliderList);
                    }
                }, 3000);

            }
        });

        txtSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarSeeMore.setVisibility(View.VISIBLE);
                txtSeeMore.setVisibility(View.GONE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getDataProduct();
                    }
                }, 2000); // delay for 100 milliseconds

            }
        });
    }

    private void setTextViewColor (TextView textView, int...color) {
        TextPaint paint =  textView.getPaint();
        float width = paint.measureText(textView.getText().toString());
        Shader shader = new LinearGradient( 0, 0, width, textView.getTextSize(), color,  null, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(shader);
        textView.setTextColor(color[0]);
    }

    private void getData() {
        mHomePagePresenter.getDataSlider();
        mHomePagePresenter.getDataNewProduct();
        mHomePagePresenter.getDataTrendingProduct();
        mHomePagePresenter.getDataOrderProduct();
        mHomePagePresenter.getDataCategory();
        getDataProduct();
    }

    int page = 1;
    private  void getDataProduct(){
        getCurrentPageProduct(page);
        page = page + 1;
        getNextPageProduct(page);
    }

    private void getNextPageProduct(int page ) {
        mHomePagePresenter.getNextPageProduct(page);
    }
    private void getCurrentPageProduct(int page) {
        mHomePagePresenter.getCurrentPageProduct(page);
    }


    private void initUI(View mView) {

        loSlider = mView.findViewById(R.id.loSlider);
        loNewProduct = mView.findViewById(R.id.loNewProduct);
        loOrderProduct = mView.findViewById(R.id.loOrderProduct);
        loTrendingProduct = mView.findViewById(R.id.loTrendingProduct);
        loCategory = mView.findViewById(R.id.loCategory);
        progressBarHome = mView.findViewById(R.id.progressBarHome);
        viewPager2Slider = mView.findViewById(R.id.viewPager2Slider);
        circleIndicator3 = mView.findViewById(R.id.circleIndicator3);

        rcViewNewProduct = mView.findViewById(R.id.recyclerviewNewProduct);
        RecyclerView.LayoutManager layoutManagerNewProduct = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false);
        rcViewNewProduct.setLayoutManager(layoutManagerNewProduct);
        rcViewNewProduct.setHasFixedSize(true);

        rcViewTrendingProduct = mView.findViewById(R.id.recyclerviewTrendingProduct);
        RecyclerView.LayoutManager layoutManagerTrendingProduct = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false);
        rcViewTrendingProduct.setLayoutManager(layoutManagerTrendingProduct);
        rcViewTrendingProduct.setHasFixedSize(true);

        rcViewOrderProduct = mView.findViewById(R.id.recyclerviewOrderProduct);
        RecyclerView.LayoutManager layoutManagerOrderProduct = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false);
        rcViewOrderProduct.setLayoutManager(layoutManagerOrderProduct);
        rcViewOrderProduct.setHasFixedSize(true);

        recyclerviewProduct = mView.findViewById(R.id.recyclerviewProduct);
        RecyclerView.LayoutManager recyclerview_product_grid_layout = new GridLayoutManager(context,2);
        recyclerviewProduct.setLayoutManager(recyclerview_product_grid_layout);
        recyclerviewProduct.setHasFixedSize(true);

        rcCategory = mView.findViewById(R.id.rcCategory);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context , 4);
        rcCategory.setLayoutManager(gridLayoutManager);
        rcCategory.setHasFixedSize(true);

        progressBarSeeMore = mView.findViewById(R.id.progressBarSeeMore);
        txtSeeMore = mView.findViewById(R.id.txtSeeMore);
        loProduct = mView.findViewById(R.id.loProduct);
        tvNewProduct = mView.findViewById(R.id.tvNewProduct);
        setTextViewColor(tvNewProduct,getResources().getColor(R.color.violet), getResources().getColor(R.color.blue), getResources().getColor(R.color.green), getResources().getColor(R.color.yellow), getResources().getColor(R.color.orange), getResources().getColor(R.color.red));
        txtOrderProduct = mView.findViewById(R.id.txtOrderProduct);
        setTextViewColor(txtOrderProduct,getResources().getColor(R.color.violet), getResources().getColor(R.color.blue), getResources().getColor(R.color.green), getResources().getColor(R.color.yellow), getResources().getColor(R.color.orange), getResources().getColor(R.color.red));
        txtTrendingProduct = mView.findViewById(R.id.txtTrendingProduct);
        setTextViewColor(txtTrendingProduct,getResources().getColor(R.color.violet), getResources().getColor(R.color.blue), getResources().getColor(R.color.green), getResources().getColor(R.color.yellow), getResources().getColor(R.color.orange), getResources().getColor(R.color.red));
        txtProduct = mView.findViewById(R.id.txtProduct);
        setTextViewColor(txtProduct,getResources().getColor(R.color.violet), getResources().getColor(R.color.blue), getResources().getColor(R.color.green), getResources().getColor(R.color.yellow), getResources().getColor(R.color.orange), getResources().getColor(R.color.red));
        txtCategory = mView.findViewById(R.id.txtCategory);
        setTextViewColor(txtCategory,getResources().getColor(R.color.violet), getResources().getColor(R.color.blue), getResources().getColor(R.color.green), getResources().getColor(R.color.yellow), getResources().getColor(R.color.orange), getResources().getColor(R.color.red));
    }

    @Override
    public void getDataSilderSuccess(List<Slider> sliderList) {
        mSliderList = sliderList;
        mSliderAdapter = new SliderAdapter(context , sliderList);
        viewPager2Slider.setAdapter(mSliderAdapter);
        circleIndicator3.setViewPager(viewPager2Slider);
        autoRunSlider(sliderList);
        progressBarHome();
        loSlider.setVisibility(View.VISIBLE);
    }

    @Override
    public void getDataNewProductSuccess(List<Product> productList) {
        mProductAdapter = new ProductAdapter(context,1, productList, new ItemProductClickListener() {
            @Override
            public void onClickItemProduct(Product product) {
                gotoProductDetails(product);
            }
        });
        rcViewNewProduct.setAdapter(mProductAdapter);
        progressBarHome();
        loNewProduct.setVisibility(View.VISIBLE);
    }

    @Override
    public void getDataTrendingProductSuccess(List<Product> productList) {
        mProductAdapter = new ProductAdapter(context,2, productList, new ItemProductClickListener() {
            @Override
            public void onClickItemProduct(Product product) {
                gotoProductDetails(product);
            }
        });
        rcViewTrendingProduct.setAdapter(mProductAdapter);
        progressBarHome();
        loTrendingProduct.setVisibility(View.VISIBLE);
    }

    @Override
    public void getDataOrderProductSuccess(List<Product> productList) {
        mProductAdapter = new ProductAdapter(context,3, productList, new ItemProductClickListener() {
            @Override
            public void onClickItemProduct(Product product) {
                gotoProductDetails(product);
            }
        });
        rcViewOrderProduct.setAdapter(mProductAdapter);
        progressBarHome();
        loOrderProduct.setVisibility(View.VISIBLE);
    }

    private void autoRunSlider(List<Slider> sliderList) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              if(viewPager2Slider.getCurrentItem() == sliderList.size()-1){
                  viewPager2Slider.setCurrentItem(0);
              }else {
                  viewPager2Slider.setCurrentItem(viewPager2Slider.getCurrentItem() + 1);
              }
            }
        }, 3000);

    }

    private void  gotoProductDetails(Product product){
        History history = HistoryDatabase.getInstance(getActivity()).historyDAO().getHistoryProduct(product.getProduct_id(), Constant.CUSTOMER_ID);
        if(history == null){
            History historyNew = new History(product.getProduct_id(), Constant.CUSTOMER_ID, product.getCategory_id(), product.getCategory_name()
                    , product.getProduct_name(), product.getProduct_desc(), product.getProduct_price(), product.getProduct_image());
            HistoryDatabase.getInstance(getActivity()).historyDAO().insertHistoryProduct(historyNew);
        }
        Intent intent = new Intent(context , ProductDetailsActivity.class);
        intent.putExtra("product" , product);
        startActivity(intent);
    }

    private void progressBarHome(){
        Constant.PROGRESSBAR_HOME_COUNT++;
        if(Constant.PROGRESSBAR_HOME_COUNT >= Constant.TOTAL_CONTENT_HOME_COUNT){
            progressBarHome.setVisibility(View.GONE);
        }
    }

    @Override
    public void getNextPageProductError() {
        txtSeeMore.setVisibility(View.GONE);
        progressBarSeeMore.setVisibility(View.GONE);
        Toast.makeText(context, "Hết Trang Rùi", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getNextPageProductSuccess(List<Product> productList) {
        Toast.makeText(context, "Current:"+productListAhihi.size()+"- Next:"+productList.size(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCurrentPageProductSuccess(List<Product> productList, int page) {
        productListAhihi.addAll(productList);
        if(mProductAdapter == null){
            mProductAdapter = new ProductAdapter(context, productListAhihi, new ItemProductClickListener() {
                @Override
                public void onClickItemProduct(Product product) {
                    gotoProductDetails(product);
                }
            });
        }else {
            mProductAdapter.setData(productListAhihi);
        }
        recyclerviewProduct.setAdapter(mProductAdapter);

        if(page > 1){
            progressBarSeeMore.setVisibility(View.GONE);
            txtSeeMore.setVisibility(View.VISIBLE);
        }
        progressBarHome();
        loProduct.setVisibility(View.VISIBLE);
    }

    @Override
    public void getDataCategoryListSuccess(List<Category> categoryList) {
        if (mCategoryAdapter == null){
            mCategoryAdapter = new CategoryAdapter(context, categoryList, new ItemCategoryClickListener() {
                @Override
                public void setOnClickItemCategory(Category category) {
                    Intent intent = new Intent(context , SimilarActivity.class);
                    intent.putExtra("category",category);
                    startActivity(intent);
                }
            });
        }else {
            mCategoryAdapter.setData(categoryList);
        }
        rcCategory.setAdapter(mCategoryAdapter);
        progressBarHome();
        loCategory.setVisibility(View.VISIBLE);
    }
}