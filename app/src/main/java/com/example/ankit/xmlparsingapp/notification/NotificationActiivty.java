package com.example.ankit.xmlparsingapp.notification;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.RenderScript;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;

import com.example.ankit.xmlparsingapp.MainActivity;
import com.example.ankit.xmlparsingapp.R;
import com.example.ankit.xmlparsingapp.alarm.AlarmActivity;
import com.example.ankit.xmlparsingapp.contentprovider.StudentActivity;

/**
 * Created by ANKIT on 9/2/2016.
 */
public class NotificationActiivty extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //simpleNotification();
        //setExpandedLayoutNotification();
        setActionNotification();
    }

    private void simpleNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("My notification")
                        .setAutoCancel(true)
                        .setContentText("Hello World!").setNumber(10).setLights(Color.GREEN, 300, 100).setVibrate(new long[]{1000, 1000, 1000, 1000, 1000}).setPriority(NotificationCompat.PRIORITY_LOW);
        ;
// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, StudentActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(StudentActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(1, mBuilder.build());
    }

    private void setExpandedLayoutNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("My notification")
                        .setAutoCancel(true)
                        .setContentText("Hello World!").setNumber(10).setLights(Color.GREEN, 300, 100).setVibrate(new long[]{1000, 1000, 1000, 1000, 1000}).setPriority(NotificationCompat.PRIORITY_MAX);
        ;
        NotificationCompat.InboxStyle inboxStyle =
                new NotificationCompat.InboxStyle();
        String[] events = new String[6];
// Sets a title for the Inbox in expanded layout
        inboxStyle.setBigContentTitle("Event tracker details:");
// Moves events into the expanded layout
        for (int i = 0; i < events.length; i++) {

            inboxStyle.addLine(events[i]);
        }
// Moves the expanded layout object into the notification object.
        mBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to "));
// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, StudentActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(StudentActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
//        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(1, mBuilder.build());
    }


    private void setActionNotification() {
        Intent myIntent = new Intent(this, StudentActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, myIntent, 0);
        Intent actionIntent = new Intent(this, MainActivity.class);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(NotificationActiivty.this)
                .setContentTitle("Notification Demo").setSmallIcon(R.mipmap.ic_launcher).setContentIntent(pendingIntent)
                .setContentText("You have got notification.");
        long[] v = {500, 1000};
        notificationBuilder.setVibrate(v);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notificationBuilder.setSound(uri);
        notificationBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to "));
        PendingIntent childPIntent = PendingIntent.getActivity(NotificationActiivty.this, 0, actionIntent, 0);
        notificationBuilder.addAction(R.drawable.abc_ic_search_api_mtrl_alpha, "Search", childPIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, notificationBuilder.build());
    }
}
