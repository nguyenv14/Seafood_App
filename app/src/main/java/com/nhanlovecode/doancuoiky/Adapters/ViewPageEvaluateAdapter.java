package com.nhanlovecode.doancuoiky.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.nhanlovecode.doancuoiky.Views.Personal.Evaluate.EvaluateHave.EvaluateHaveFragment;
import com.nhanlovecode.doancuoiky.Views.Personal.Evaluate.EvaluateWait.EvaluateWaitFragment;

public class ViewPageEvaluateAdapter extends FragmentStatePagerAdapter{

    public ViewPageEvaluateAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new EvaluateWaitFragment();
            case 1: return new EvaluateHaveFragment();
            default: return new EvaluateWaitFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Chưa đánh giá";
                break;
            case 1:
                title = "Đã đánh giá";
                break;
        }
        return title;
    }
}
