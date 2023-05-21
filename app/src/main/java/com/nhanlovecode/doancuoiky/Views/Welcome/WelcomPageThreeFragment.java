package com.nhanlovecode.doancuoiky.Views.Welcome;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.nhanlovecode.doancuoiky.R;
import com.nhanlovecode.doancuoiky.Views.LoginAndSignup.LoginAndSignupActivity;
import com.nhanlovecode.doancuoiky.Views.Main.MainActivity;

public class WelcomPageThreeFragment extends Fragment {

    View mView;
    TextView btnStart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_welcom_page_three, container, false);
        initUI(mView);
        eventClick();
        return mView;
    }

    private void eventClick() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginAndSignupActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initUI(View mView) {
        btnStart = mView.findViewById(R.id.btnStart);
    }

}