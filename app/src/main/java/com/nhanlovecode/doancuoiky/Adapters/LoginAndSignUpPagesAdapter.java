package com.nhanlovecode.doancuoiky.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.nhanlovecode.doancuoiky.Views.LoginAndSignup.Login.LoginTabFragment;
import com.nhanlovecode.doancuoiky.Views.LoginAndSignup.SignUp.SignUpTabFragment;

public class LoginAndSignUpPagesAdapter extends FragmentStateAdapter {
    public LoginAndSignUpPagesAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1){
            return new SignUpTabFragment();
        }
        return new LoginTabFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
