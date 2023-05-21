package com.nhanlovecode.doancuoiky.Views.Welcome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nhanlovecode.doancuoiky.Adapters.WelcomPagesAdapter;
import com.nhanlovecode.doancuoiky.R;

import me.relex.circleindicator.CircleIndicator;

public class WelcomActivity extends AppCompatActivity implements WelcomMVPView{

    private TextView tvSkip;
    private ViewPager viewPager;
    private RelativeLayout layoutBottom;
    private CircleIndicator circleIndicator;
    private LinearLayout layoutNext;

    private WelcomPagesAdapter mWelcomPagesAdapter;
    private WelcomPresenter mWelcomPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        initUI();
        eventClick();

    }

    private void eventClick() {
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
            }
        });

        layoutNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager.getCurrentItem() < 2){
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2){
                    tvSkip.setVisibility(View.GONE);
                    layoutBottom.setVisibility(View.GONE);
                }else {
                    tvSkip.setVisibility(View.VISIBLE);
                    layoutBottom.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initUI() {
        tvSkip = findViewById(R.id.tvSkip);
        viewPager = findViewById(R.id.viewPager);
        layoutBottom = findViewById(R.id.layoutBottom);
        circleIndicator = findViewById(R.id.circleIndicator);
        layoutNext = findViewById(R.id.layoutNext);

        mWelcomPresenter = new WelcomPresenter(this , this);
        mWelcomPagesAdapter = new WelcomPagesAdapter(getSupportFragmentManager() , FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(mWelcomPagesAdapter);
        circleIndicator.setViewPager(viewPager);
    }
}