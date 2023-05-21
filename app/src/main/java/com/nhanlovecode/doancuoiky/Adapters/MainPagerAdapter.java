package com.nhanlovecode.doancuoiky.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.nhanlovecode.doancuoiky.Views.Category.CategoryPageFragment;
import com.nhanlovecode.doancuoiky.Views.Favorite.FavoritePageFragment;
import com.nhanlovecode.doancuoiky.Views.Home.HomePageFragment;
import com.nhanlovecode.doancuoiky.Views.Order.OrderPageFragment;
import com.nhanlovecode.doancuoiky.Views.Personal.PersonalPageFragment;

public class MainPagerAdapter extends FragmentStateAdapter {


    public MainPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomePageFragment();
            case 1:
                return new CategoryPageFragment();
            case 2:
                return new OrderPageFragment();
            case 3:
                return new FavoritePageFragment();
            case 4:
                return new PersonalPageFragment();
            default:
                return new HomePageFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
