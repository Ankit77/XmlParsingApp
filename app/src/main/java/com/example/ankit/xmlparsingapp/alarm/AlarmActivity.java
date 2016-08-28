package com.example.ankit.xmlparsingapp.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ankit.xmlparsingapp.R;

import java.util.Calendar;
//http://stacktips.com/tutorials/android/repeat-alarm-example-in-android

/**
 * Created by ANKIT on 8/27/2016.
 */
public class AlarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_main);
//        SetOneTimeAlarm(AlarmActivity.this);
//        SetRepeatingAlarm(AlarmActivity.this);
        deleteAlarm(AlarmActivity.this);
    }

    public void SetOneTimeAlarm(Context context) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MILLISECOND, 1000 * 10);
        //After after 5 seconds
        if (Build.VERSION.SDK_INT > 18) {
            am.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi);
        } else {
            am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi);
        }
    }


    public void SetRepeatingAlarm(Context context) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 2, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MILLISECOND, 1000 * 10);

        //After after 5 seconds
        if (Build.VERSION.SDK_INT > 18) {
            am.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60, pi);
        } else {
            am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60, pi);
        }
    }


    public void deleteAlarm(Context context) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 2, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        am.cancel(pi);
    }


}
