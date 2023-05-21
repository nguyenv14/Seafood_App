package com.nhanlovecode.doancuoiky.Views.Personal.Evaluate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.nhanlovecode.doancuoiky.Adapters.ViewPageEvaluateAdapter;
import com.nhanlovecode.doancuoiky.R;

public class EvaluateActivity extends AppCompatActivity {
    Toolbar toolBar;
    TabLayout tabLayout;
    ViewPager viewPager;

    private ViewPageEvaluateAdapter viewPageEvaluateAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        initUI();
        eventClick();
    }

    private void eventClick() {
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initUI() {
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewPagerEvaluate);
        toolBar  = findViewById(R.id.toolBar);

        viewPageEvaluateAdapter = new ViewPageEvaluateAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPageEvaluateAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}