package com.example.ankit.xmlparsingapp.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ANKIT on 8/27/2016.
 */
public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(AlarmManagerBroadcastReceiver.class.getSimpleName(), "Alarm is call");
        Toast.makeText(context, "Alarm is fired", Toast.LENGTH_LONG).show();
    }
}
