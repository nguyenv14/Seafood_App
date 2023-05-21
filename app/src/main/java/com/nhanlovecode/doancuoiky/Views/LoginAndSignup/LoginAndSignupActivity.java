package com.nhanlovecode.doancuoiky.Views.LoginAndSignup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.nhanlovecode.doancuoiky.Adapters.LoginAndSignUpPagesAdapter;
import com.nhanlovecode.doancuoiky.R;

public class LoginAndSignupActivity extends AppCompatActivity implements LoginAndSignupMVPView {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private LoginAndSignUpPagesAdapter mLoginAndSignUpPagesAdapter;

    LoginAndSignupPresenter mLoginAndSignupPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_signup);
        mLoginAndSignupPresenter = new LoginAndSignupPresenter(this , this);

        initUI();
        eventClick();

    }

    private void eventClick() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }

    private void initUI() {
        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.view_pager);

        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Signup"));

        FragmentManager fragmentManager = getSupportFragmentManager();
        mLoginAndSignUpPagesAdapter = new LoginAndSignUpPagesAdapter(fragmentManager, getLifecycle());
        viewPager2.setAdapter(mLoginAndSignUpPagesAdapter);
    }
}