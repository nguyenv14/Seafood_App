
package com.nhanlovecode.doancuoiky.Views.Order;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.nhanlovecode.doancuoiky.Adapters.OrderPagesAdapter;
import com.nhanlovecode.doancuoiky.R;

public class OrderPageFragment extends Fragment implements  OrderPageMVPView{

    TabLayout tabLayout;
    ViewPager2 viewPager2;

    OrderPagesAdapter mOrderPagesAdapter;
    OrderPagePresenter mOrderPagePresenter;
    Context context;
    View mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        mOrderPagePresenter = new OrderPagePresenter(context , this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_order_page, container, false);

        initUI(mView);
        eventClick();

        return mView;
    }

    private void eventClick() {
    }

    private void initUI(View mView) {
        tabLayout = mView.findViewById(R.id.tabLayout);
        viewPager2 = mView.findViewById(R.id.viewPager2);

        mOrderPagesAdapter = new OrderPagesAdapter(this);
        viewPager2.setAdapter(mOrderPagesAdapter);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Đang xử lý");
                        break;
                    case 1:
                        tab.setText("Đang giao");
                        break;
                    case 2:
                        tab.setText("Hoàn thành");
                        break;
                    case 3:
                        tab.setText("Đã Hủy");
                        break;
                }
            }
        }).attach();
    }
}