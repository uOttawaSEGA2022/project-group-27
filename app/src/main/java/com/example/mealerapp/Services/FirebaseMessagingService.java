package com.example.mealerapp.Services;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.mealerapp.Activity.MainActivity;
import com.example.mealerapp.Objects.Notification;
import com.example.mealerapp.R;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    private final String channelId = "notfications";
    private final String channelName = "mealer";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {

        if(message.getNotification() != null){
            generateNotifcation(message.getNotification().getTitle(), message.getNotification().getBody());
        }
    }

    public void generateNotifcation(String title, String description){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setSmallIcon(R.drawable.mealer_logo1)
                .setAutoCancel(true).setOnlyAlertOnce(true).setContentIntent(pendingIntent);

        builder = builder.setContent(getRemoteView(title, description));

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel  notificationChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);

        }


        notificationManager.notify(0, builder.build());

    }


    public RemoteViews getRemoteView(String title, String description){
        @SuppressLint("RemoteViewLayout") RemoteViews remoteView = new RemoteViews("com.example.mealerapp", R.layout.notification);

        remoteView.setTextViewText(R.id.title, title);
        remoteView.setTextViewText(R.id.description, description);
        remoteView.setImageViewResource(R.id.app_logo, R.drawable.mealer_logo1);

        return remoteView;
    }


}
