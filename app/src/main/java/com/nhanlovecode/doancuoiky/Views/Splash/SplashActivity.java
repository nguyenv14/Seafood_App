package com.nhanlovecode.doancuoiky.Views.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.nhanlovecode.doancuoiky.R;
import com.nhanlovecode.doancuoiky.Views.LoginAndSignup.LoginAndSignupActivity;
import com.nhanlovecode.doancuoiky.Views.Main.MainActivity;
import com.nhanlovecode.doancuoiky.Views.Welcome.WelcomActivity;

public class SplashActivity extends AppCompatActivity implements SplashMVPView{
    SplashPresenter mSplashPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mSplashPresenter = new SplashPresenter(this , this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSplashPresenter.checkFirstInstallApp();
            }
        },2000);

    }

    @Override
    public void isFirstInstallApp() {
        Intent intent = new Intent(SplashActivity.this , WelcomActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void noFirstInstallApp() {
        mSplashPresenter.checkSessionLogin();
    }

    @Override
    public void isLogged() {
        Intent intent = new Intent(SplashActivity.this , MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void notLogged() {
        Intent intent = new Intent(SplashActivity.this , LoginAndSignupActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}