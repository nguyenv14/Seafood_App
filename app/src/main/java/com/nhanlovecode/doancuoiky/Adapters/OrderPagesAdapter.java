package com.nhanlovecode.doancuoiky.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.nhanlovecode.doancuoiky.Views.Order.OrderCancelledTab.OrderCancelledTabFragment;
import com.nhanlovecode.doancuoiky.Views.Order.OrderCompletedTab.OrderCompletedTabFragment;
import com.nhanlovecode.doancuoiky.Views.Order.OrderDeliveringTab.OrderDeliveringTabFragment;
import com.nhanlovecode.doancuoiky.Views.Order.OrderProcessingTab.OrderProcessingTabFragment;

public class OrderPagesAdapter extends FragmentStateAdapter {


    public OrderPagesAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new OrderProcessingTabFragment();
            case 1:
                return new OrderDeliveringTabFragment();
            case 2:
                return new OrderCompletedTabFragment();
            case 3:
                return new OrderCancelledTabFragment();
            default:
                return new OrderProcessingTabFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }


}
