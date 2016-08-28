package com.example.ankit.xmlparsingapp.alarm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.ankit.xmlparsingapp.R;

/**
 * Created by ANKIT on 8/28/2016.
 */
public class HandlerActivity extends AppCompatActivity {
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler.postDelayed(runnable, 15000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        handler.removeCallbacks(runnable);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(HandlerActivity.this, AlarmActivity.class);
            startActivity(intent);
            Toast.makeText(HandlerActivity.this, "Handler is fired", Toast.LENGTH_LONG).show();
        }
    };
}
