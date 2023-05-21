package com.nhanlovecode.doancuoiky.Views.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.nhanlovecode.doancuoiky.Adapters.MainPagerAdapter;
import com.nhanlovecode.doancuoiky.R;
import com.nhanlovecode.doancuoiky.Views.Cart.CartActivity;
import com.nhanlovecode.doancuoiky.Views.Search.SearchViewActivity;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends AppCompatActivity implements MainMVPView {

    private LinearLayout loToolbarSearch;
    private MainPresenter mainPresenter;
    private ViewPager2 viewPager2;
    private BottomNavigationView bottomNavigation;
    private MainPagerAdapter mMainPagerAdapter;
    private SearchView seaViewHome;
    private ImageView imgCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter(this,this);
        initUI();
        eventClick();
    }

    @Override
    protected void onResume() {
        super.onResume();
        seaViewHome.clearFocus();
    }

    private void eventClick() {
        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        seaViewHome.setOnClickListener(view -> {
            int currentIndex = viewPager2.getCurrentItem();
            if (currentIndex == 0 || currentIndex == 1){
                Intent intent = new Intent(this, SearchViewActivity.class);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(this, R.anim.zoom_in, R.anim.zoom_out);
                startActivity(intent, options.toBundle());
            }
        });


        seaViewHome.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                EventBus.getDefault().post(new MyEvent(newText));

                return false;
            }
        });

        seaViewHome.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    // Xử lý khi SearchView được focus
                    int currentIndex = viewPager2.getCurrentItem();
                    if (currentIndex == 0 || currentIndex == 1){
                    Intent intent = new Intent(MainActivity.this, SearchViewActivity.class);
                    ActivityOptions options = ActivityOptions.makeCustomAnimation(MainActivity.this, R.anim.zoom_in, R.anim.zoom_out);
                    startActivity(intent, options.toBundle());
                    }

                } else {
                    // Xử lý khi SearchView mất focus
                }
            }
        });

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bottom_home:
                        viewPager2.setCurrentItem(0);
                        loToolbarSearch.setVisibility(View.VISIBLE);
                        seaViewHome.setQueryHint("Bạn tìm gì hôm nay ?");
                        break;
                    case R.id.bottom_category:
                        viewPager2.setCurrentItem(1);
                        loToolbarSearch.setVisibility(View.VISIBLE);
                        seaViewHome.setQueryHint("Bạn tìm gì hôm nay ?");
                        break;
                    case R.id.bottom_order:
                        viewPager2.setCurrentItem(2);
                        loToolbarSearch.setVisibility(View.VISIBLE);
                        seaViewHome.setQueryHint("Tìm kiếm đơn hàng ?");
                        break;
                    case R.id.bottom_favorite:
                        viewPager2.setCurrentItem(3);
                        loToolbarSearch.setVisibility(View.GONE);
                        break;
                    case R.id.bottom_personal:
                        viewPager2.setCurrentItem(4);
                        loToolbarSearch.setVisibility(View.GONE);
                        break;
                }
                return true;
            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        bottomNavigation.getMenu().findItem(R.id.bottom_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigation.getMenu().findItem(R.id.bottom_category).setChecked(true);
                        break;
                    case 2:
                        bottomNavigation.getMenu().findItem(R.id.bottom_order).setChecked(true);
                        break;
                    case 3:
                        bottomNavigation.getMenu().findItem(R.id.bottom_favorite).setChecked(true);
                        break;
                    case 4:
                        bottomNavigation.getMenu().findItem(R.id.bottom_personal).setChecked(true);
                        break;
                }
            }
        });
    }

    private void initUI() {
        viewPager2 = findViewById(R.id.viewPager2);
        viewPager2.setUserInputEnabled(false);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setItemIconTintList(null);
        mMainPagerAdapter = new MainPagerAdapter(this);
        viewPager2.setAdapter(mMainPagerAdapter);
        imgCart = findViewById(R.id.imgCart);
        seaViewHome = findViewById(R.id.seaViewHome);
        loToolbarSearch  = findViewById(R.id.loToolbarSearch);
    }

    // Class định nghĩa sự kiện
    public class MyEvent {
        private String data;

        public MyEvent(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }
    }

}

