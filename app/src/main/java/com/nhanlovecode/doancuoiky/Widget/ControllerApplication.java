package com.nhanlovecode.doancuoiky.Widget;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.DatabaseLocal.SharedPreferences.MySharedPreferencesManager;


public class ControllerApplication extends Application {

    public static final String CHANNEL_ID = Constant.PUSH_NOTIFICATION_ID;

    @Override
    public void onCreate() {
        super.onCreate();
        MySharedPreferencesManager.init(getApplicationContext());
        createChanneNotification();

    }

    private void createChanneNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID , Constant.NAME_NOTIFICATION_ONE , NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
