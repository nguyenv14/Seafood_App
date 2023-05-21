package com.nhanlovecode.doancuoiky.Views.ProductDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nhanlovecode.doancuoiky.Adapters.ImageDetailsProductAdapter;
import com.nhanlovecode.doancuoiky.Adapters.ProductAdapter;
import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseLocal.RoomDatabase.FavoriteDatabase;
import com.nhanlovecode.doancuoiky.DatabaseLocal.RoomDatabase.HistoryDatabase;
import com.nhanlovecode.doancuoiky.DatabaseLocal.SharedPreferences.MySharedPreferencesManager;
import com.nhanlovecode.doancuoiky.EventClickListener.ItemProductClickListener;
import com.nhanlovecode.doancuoiky.Models.Cart;
import com.nhanlovecode.doancuoiky.Models.Comment;
import com.nhanlovecode.doancuoiky.Models.Favorite;
import com.nhanlovecode.doancuoiky.Models.GalleryProduct;
import com.nhanlovecode.doancuoiky.Models.History;
import com.nhanlovecode.doancuoiky.Models.Product;
import com.nhanlovecode.doancuoiky.R;
import com.nhanlovecode.doancuoiky.Views.Cart.CartActivity;
import com.nhanlovecode.doancuoiky.Views.CheckOut.CheckOutActivity;
import com.nhanlovecode.doancuoiky.Views.Comment.CommentActivity;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity implements ProductDetailsMVPView{

    private Toolbar toolBar;
    private NestedScrollView nestedScrollView;
    private BottomSheetDialog bottomSheetDialog;
    private TextView bsTxtTitleAddCart;
    private ImageView bsImgCancel;
    private ImageView bsImgProduct;
    private ImageView bsImgMinus;
    private ImageView bsImgPlus;
    private TextView bsTxtNameProduct;
    private TextView bsTxtPriceProduct;
    private TextView bsTxtQuantityProduct;
    private TextView bsBtnByNow;
    private TextView bsBtnAddCart;
    private ImageView imgStarLightOne , imgStarLightTwo , imgStarLightThree , imgStarLightFour , imgStarLightFive;
    private ImageView imgStarBlackOne , imgStarBlackTwo , imgStarBlackThree , imgStarBlackFour , imgStarBlackFive;
    private TextView tvRankEvaluate , tvTotalEvaluate , txtSeeMoreCmt;

    private LinearLayout loOrderProduct , loProductByCategory;

    private ImageView imgBack , imgCart , imgAvtProduct, btnAddFavorite, btnRemoveFavorite ;
    private TextView txtNameProduct , txtPriceProduct ;
    private RecyclerView rcViewGalleryProduct , rcViewProductByCategory , rcViewOrderProduct;
    private TextView btnAddCart , btnBuyNow ;

    private ProductDetailsPresenter mProductDetailsPresenter;
    private ProductAdapter mProductAdapter;
    private ImageDetailsProductAdapter mImageDetailsProductAdapter;

    private TextView txtTitleToolBar;
    private ImageView imgCartToolbar;

    private Product mProduct;
    private String mStrComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        mProductDetailsPresenter = new ProductDetailsPresenter(this , this);
        initUI();
        initUIBottomSheetDialogAddCart();
        getData();
        eventClick();
        eventScroll();
    }

    private void eventScroll() {
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // Xử lý sự kiện cuộn xuống ở đây
                if (scrollY >= 105){
                    toolBar.setVisibility(View.VISIBLE);
                }else {
                    toolBar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void getData() {
        Intent intent = getIntent();
        mProductDetailsPresenter.getDataProduct(intent);
        mProductDetailsPresenter.getDataProductByCategory(mProduct.getCategory_id(), mProduct.getProduct_id());
        mProductDetailsPresenter.getDataOrderProduct();
        mProductDetailsPresenter.getDataGalleryProduct(mProduct.getProduct_id());
        mProductDetailsPresenter.getDataEvaluateProduct(mProduct.getProduct_id());
        mProductDetailsPresenter.getDataFavorite(mProduct.getProduct_id());
    }

    private void eventClick() {
        eventClickBack();
        eventClickCart();
        eventClickFavorite();
        eventClickSeeMoreCmt();
        eventClickToolBar();
        eventClickBack();
        eventClickBottomSheetDialog();
    }

    private void eventClickBottomSheetDialog() {
        bsImgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        bsImgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt((String) bsTxtQuantityProduct.getText());
                mProductDetailsPresenter.minusQuantityProduct(mProduct , quantity);
            }
        });

        bsImgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt((String) bsTxtQuantityProduct.getText());
                mProductDetailsPresenter.plusQuantityProduct(mProduct , quantity);
            }
        });
        bsBtnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProductDetailsPresenter.addCart(getDataBSCart());
            }
        });

        bsBtnByNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Cart> mCartListChecked = new ArrayList<>();
                mCartListChecked.add(getDataBSCart());
                Intent intent = new Intent(ProductDetailsActivity.this , CheckOutActivity.class);
                Gson gson = new Gson();
                Type type = new TypeToken<List<Cart>>(){}.getType();
                String strCartListChecked =  gson.toJson(mCartListChecked, type);
                intent.putExtra("strCartListChecked" , strCartListChecked);
                startActivity(intent);
            }
        });

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.show();
            }
        });

        btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.show();
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
        imgCartToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailsActivity.this , CartActivity.class);
                startActivity(intent);
            }
        });
    }


    private void eventClickSeeMoreCmt() {
        txtSeeMoreCmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , CommentActivity.class);
                intent.putExtra("strComment", mStrComment);
                startActivity(intent);
            }
        });
    }

    private void eventClickFavorite() {
        btnAddFavorite.setOnClickListener(view -> {
            mProductDetailsPresenter.addProductToFavorite(mProduct);
        });

        btnRemoveFavorite.setOnClickListener(view -> {
            mProductDetailsPresenter.removeProductToFavorite(mProduct);
        });
    }

    private Cart getDataBSCart(){
        int product_quantity = Integer.parseInt(bsTxtQuantityProduct.getText().toString());
        int customer_id = MySharedPreferencesManager.getCustomer(Constant.PREF_KEY_CUSTOMER).getCustomer_id();
        Cart cart = new Cart(
                mProduct.getProduct_id(),
                customer_id,
                mProduct.getProduct_name(),
                mProduct.getProduct_price(),
                mProduct.getProduct_image(),
                product_quantity);
        return cart;
    }

    private void eventClickCart() {
        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailsActivity.this , CartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void eventClickBack() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initUI() {
        imgStarLightOne = findViewById(R.id.imgStarLightOne);
        imgStarLightTwo = findViewById(R.id.imgStarLightTwo);
        imgStarLightThree = findViewById(R.id.imgStarLightThree);
        imgStarLightFour = findViewById(R.id.imgStarLightFour);
        imgStarLightFive = findViewById(R.id.imgStarLightFive);

        imgStarBlackOne = findViewById(R.id.imgStarBlackOne);
        imgStarBlackTwo = findViewById(R.id.imgStarBlackTwo);
        imgStarBlackThree = findViewById(R.id.imgStarBlackThree);
        imgStarBlackFour = findViewById(R.id.imgStarBlackFour);
        imgStarBlackFive = findViewById(R.id.imgStarBlackFive);

        tvRankEvaluate = findViewById(R.id.tvRankEvaluate);
        tvTotalEvaluate = findViewById(R.id.tvTotalEvaluate);
        txtSeeMoreCmt = findViewById(R.id.txtSeeMoreCmt);

        imgBack = findViewById(R.id.imgBack);
        imgCart = findViewById(R.id.imgCart);
        imgAvtProduct = findViewById(R.id.imgAvtProduct);
        txtNameProduct = findViewById(R.id.txtNameProduct);
        txtPriceProduct = findViewById(R.id.txtPriceProduct);

        rcViewOrderProduct = findViewById(R.id.rcViewOrderProduct);
        RecyclerView.LayoutManager layoutManagerOrderProduct = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        rcViewOrderProduct.setLayoutManager(layoutManagerOrderProduct);
        rcViewOrderProduct.setHasFixedSize(true);

        rcViewProductByCategory = findViewById(R.id.rcViewProductByCategory);
        RecyclerView.LayoutManager layoutManagerProByCate = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        rcViewProductByCategory.setLayoutManager(layoutManagerProByCate);
        rcViewProductByCategory.setHasFixedSize(true);

        rcViewGalleryProduct = findViewById(R.id.rcViewGalleryProduct);
        RecyclerView.LayoutManager layoutManagerGallery = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        rcViewGalleryProduct.setLayoutManager(layoutManagerGallery);
        rcViewGalleryProduct.setHasFixedSize(true);

        btnAddCart = findViewById(R.id.btnAddCart);
        btnBuyNow = findViewById(R.id.btnBuyNow);

        loOrderProduct = findViewById(R.id.loOrderProduct);
        loProductByCategory = findViewById(R.id.loProductByCategory);

        btnAddFavorite = findViewById(R.id.btnAddFavorite);
        btnRemoveFavorite = findViewById(R.id.btnRemoveFavorite);

        nestedScrollView = findViewById(R.id.nestedScrollView);
        toolBar = findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        txtTitleToolBar = findViewById(R.id.txtTitleToolBar);
        setTextViewColor(txtTitleToolBar,getResources().getColor(R.color.violet), getResources().getColor(R.color.blue), getResources().getColor(R.color.green), getResources().getColor(R.color.yellow), getResources().getColor(R.color.orange), getResources().getColor(R.color.red));
        imgCartToolbar = findViewById(R.id.imgCartToolbar);
    }

    private void initUIBottomSheetDialogAddCart() {
        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setContentView(R.layout.bottomsheet_product_details);


        bsTxtTitleAddCart = bottomSheetDialog.findViewById(R.id.bsTxtTitleAddCart);
        bsImgCancel = bottomSheetDialog.findViewById(R.id.bsImgCancel);
        bsImgProduct = bottomSheetDialog.findViewById(R.id.bsImgProduct);
        bsImgMinus = bottomSheetDialog.findViewById(R.id.bsImgMinus);
        bsImgPlus = bottomSheetDialog.findViewById(R.id.bsImgPlus);

        bsTxtNameProduct = bottomSheetDialog.findViewById(R.id.bsTxtNameProduct);
        bsTxtPriceProduct = bottomSheetDialog.findViewById(R.id.bsTxtPriceProduct);
        bsTxtQuantityProduct = bottomSheetDialog.findViewById(R.id.bsTxtQuantityProduct);
        bsBtnByNow = bottomSheetDialog.findViewById(R.id.bsBtnByNow);
        bsBtnAddCart = bottomSheetDialog.findViewById(R.id.bsBtnAddCart);
        setTextViewColor(bsTxtTitleAddCart,getResources().getColor(R.color.violet), getResources().getColor(R.color.blue), getResources().getColor(R.color.green), getResources().getColor(R.color.yellow), getResources().getColor(R.color.orange), getResources().getColor(R.color.red));
    }

    private void setTextViewColor (TextView textView, int...color) {
        TextPaint paint =  textView.getPaint();
        float width = paint.measureText(textView.getText().toString());
        Shader shader = new LinearGradient( 0, 0, width, textView.getTextSize(), color,  null, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(shader);
        textView.setTextColor(color[0]);
    }

    @Override
    public void getDataProductSuccess(Product product) {
        mProduct = product;
        txtTitleToolBar.setText(product.getProduct_name());
        txtNameProduct.setText(product.getProduct_name());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtPriceProduct.setText("Giá: "+decimalFormat.format(product.getProduct_price())+" đ");
        Glide.with(this).load(product.getProduct_image()).into(imgAvtProduct);

        bsTxtNameProduct.setText(product.getProduct_name());
        Glide.with(this).load(product.getProduct_image()).into(bsImgProduct);
        bsTxtPriceProduct.setText("Giá: "+decimalFormat.format(product.getProduct_price())+" đ");
        bsTxtQuantityProduct.setText("1");
    }

    @Override
    public void getDataProductError() {
        finish();
        Toast.makeText(this, "Không Thể Lấy Dữ Liệu ! Hãy Thử Vào Lại !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getDataProductByCategorySuccess(List<Product> productList) {
        mProductAdapter = new ProductAdapter(this, productList, new ItemProductClickListener() {
            @Override
            public void onClickItemProduct(Product product) {
                gotoProductDetails(product);
            }
        });
        rcViewProductByCategory.setAdapter(mProductAdapter);
    }

    @Override
    public void getDataProductByCategoryError() {
        loProductByCategory.setVisibility(View.GONE);
    }

    @Override
    public void getDataOrderProductSuccess(List<Product> productList) {
        mProductAdapter = new ProductAdapter(this, productList, new ItemProductClickListener() {
            @Override
            public void onClickItemProduct(Product product) {
                gotoProductDetails(product);
            }
        });
        rcViewOrderProduct.setAdapter(mProductAdapter);
    }

    @Override
    public void getDataOrderProductError() {
        loOrderProduct.setVisibility(View.GONE);
    }

    @Override
    public void getDataGalleryProductSuccess(List<GalleryProduct> galleryProductList) {
        mImageDetailsProductAdapter = new ImageDetailsProductAdapter(this , galleryProductList);
        rcViewGalleryProduct.setAdapter(mImageDetailsProductAdapter);
    }

    @Override
    public void getDataGalleryProductError() {

    }

    @Override
    public void getDataStarProductSuccess(int numStars, Double avgStar, int totalEval) {
        for (int i = 1; i <= numStars; i++) {
            switch (i) {
                case 1:
                    imgStarLightOne.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    imgStarLightTwo.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    imgStarLightThree.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    imgStarLightFour.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    imgStarLightFive.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        }

        for (int i = numStars + 1; i <= 5; i++) {
            switch (i) {
                case 1:
                    imgStarBlackOne.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    imgStarBlackTwo.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    imgStarBlackThree.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    imgStarBlackFour.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    imgStarBlackFive.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        }
        tvRankEvaluate.setText(avgStar+"/5.0");
        tvTotalEvaluate.setText(totalEval+" đánh giá");
    }

    @Override
    public void getDataStarProductError() {
        for (int i = 1; i <= 5; i++) {
            switch (i) {
                case 1:
                    imgStarBlackOne.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    imgStarBlackTwo.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    imgStarBlackThree.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    imgStarBlackFour.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    imgStarBlackFive.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        }
        tvRankEvaluate.setText("0.0/5.0");
        tvTotalEvaluate.setText("0 đánh giá");
        txtSeeMoreCmt.setVisibility(View.GONE);
    }

    private void gotoProductDetails(Product product){
        History history = HistoryDatabase.getInstance(getApplicationContext()).historyDAO().getHistoryProduct(product.getProduct_id(), Constant.CUSTOMER_ID);
        if(history == null){
            History historyNew = new History(product.getProduct_id(), Constant.CUSTOMER_ID, product.getCategory_id(), product.getCategory_name()
                    , product.getProduct_name(), product.getProduct_desc(), product.getProduct_price(), product.getProduct_image());
            HistoryDatabase.getInstance(this).historyDAO().insertHistoryProduct(historyNew);
        }
        Intent intent = new Intent(this , ProductDetailsActivity.class);
        intent.putExtra("product" , product);
        startActivity(intent);
    }

    @Override
    public void setQuantityProductSuccess(int quantity, Double totalPriceProduct) {
        bsTxtQuantityProduct.setText(quantity+"");
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        bsTxtPriceProduct.setText("Giá: "+decimalFormat.format(totalPriceProduct)+" đ");
    }

    @Override
    public void setQuantityProductError() {
        Toast.makeText(this, "Số lượng không hợp lệ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void putCartError() {
        Toast.makeText(this, "Sản Phẫm Đã Tồn Tại Trong Giỏ Hàng", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void putCartSuccess() {
        Toast.makeText(this, "Thêm Vào Giỏ Hàng Thành Công", Toast.LENGTH_SHORT).show();
        bottomSheetDialog.dismiss();
    }

    @Override
    public void actionAddProductToFavorite() {
        Toast.makeText(this, "Đã thêm vào Yêu thích!", Toast.LENGTH_SHORT).show();
        btnRemoveFavorite.setVisibility(View.VISIBLE);
        btnAddFavorite.setVisibility(View.GONE);
    }

    @Override
    public void actionRemoveProductToFavorite() {
        Toast.makeText(this, "Đã xóa khỏi Yêu thích!", Toast.LENGTH_SHORT).show();
        btnAddFavorite.setVisibility(View.VISIBLE);
        btnRemoveFavorite.setVisibility(View.GONE);
    }

    @Override
    public void getDataEvaluateProductSuccess(List<Comment> commentList) {
        Gson gson = new Gson();
        String strComment = gson.toJson(commentList);
        mStrComment = strComment;
    }

    @Override
    public void getgetDataFavoriteError() {
        btnAddFavorite.setVisibility(View.VISIBLE);
        btnRemoveFavorite.setVisibility(View.GONE);
    }

    @Override
    public void getgetDataFavoriteSuccess() {
        btnAddFavorite.setVisibility(View.GONE);
        btnRemoveFavorite.setVisibility(View.VISIBLE);
    }
}