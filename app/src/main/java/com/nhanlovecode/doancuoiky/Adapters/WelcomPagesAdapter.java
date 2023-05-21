package com.nhanlovecode.doancuoiky.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.nhanlovecode.doancuoiky.Views.Welcome.WelcomPageOneFragment;
import com.nhanlovecode.doancuoiky.Views.Welcome.WelcomPageThreeFragment;
import com.nhanlovecode.doancuoiky.Views.Welcome.WelcomPageTwoFragment;

public class WelcomPagesAdapter extends FragmentStatePagerAdapter {

    public WelcomPagesAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new WelcomPageOneFragment() ;
            case 1:
                return new WelcomPageTwoFragment() ;
            case 2:
                return new WelcomPageThreeFragment() ;
            default:
                return new  WelcomPageOneFragment() ;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
