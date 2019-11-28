package org.ccomp.service.notification;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.navigation.NavDeepLinkBuilder;

import org.ccomp.MainActivity;
import org.ccomp.R;

import org.ccomp.data.application.NotificationData;

public class NotificationService {

    private Context context;

    public NotificationService(Context application){

        this.context = application;
    }

    public void createNotificationChannel(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "C1";
            String description = "DESCRIPTION";

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("C1", name, importance);

            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void createNotification(NotificationData data){

        PendingIntent intent = new NavDeepLinkBuilder(context).setComponentName(MainActivity.class)
                .setGraph(data.getGraph())
                .setDestination(data.getDestination())
                .setArguments(data.getArguments())
                .createPendingIntent();


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "C1")
                .setSmallIcon(data.getIcon())
                .setContentTitle(data.getTitle())
                .setContentText(data.getText())
                .setPriority(data.getPriority())
                .setContentIntent(intent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(1, builder.build());
    }
}
