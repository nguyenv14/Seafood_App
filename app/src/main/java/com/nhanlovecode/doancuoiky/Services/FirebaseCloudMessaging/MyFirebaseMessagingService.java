package com.nhanlovecode.doancuoiky.Services.FirebaseCloudMessaging;

import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.nhanlovecode.doancuoiky.Constant.Constant;
import com.nhanlovecode.doancuoiky.R;
import com.nhanlovecode.doancuoiky.Services.Notification.Notification;


import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

     public static final String TAG = MyFirebaseMessagingService.class.getName();

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);


//        RemoteMessage.Notification notification = message.getNotification();
//        if (notification ==null){
//            return;
//        }

//        String tt = notification.getTitle();
//        String bd = notification.getBody();

        Map<String,String> stringMap = message.getData();
        String tt = stringMap.get("title");
        String bd = stringMap.get("body");

        sendNotification(tt,bd);

    }

    private void sendNotification(String tt, String bd) {

//        Intent intent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this , 0 , intent ,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notibuilder = new NotificationCompat.Builder(this , Constant.PUSH_NOTIFICATION_ID)
                .setContentTitle(tt)
                .setContentText(bd)
                .setSmallIcon(R.mipmap.ic_launcher);
//                .setContentIntent(pendingIntent);

        android.app.Notification notification =  notibuilder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if(notificationManager != null){
            notificationManager.notify(1,notification);
        }
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        // feMqGOgiRA68FoUhtH5y9U:APA91bFL2wCu2clpPxUZm6QI3lFOFbb1stbEIXFmT_njfPoR6tmKGRZumAbTwGgRJlxFeXddK3wGi4GYQZpgZ4ntdhnW6dbdyCrt81BE53rn7W1Vn-FhXwi4Ozj6VWaC4B3JMWA0EGP3
        Log.e(TAG,token);
    }

}
