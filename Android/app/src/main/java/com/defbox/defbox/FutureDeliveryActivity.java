package com.defbox.defbox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.defbox.defbox.adapter.Back4app;

public class FutureDeliveryActivity extends AppCompatActivity implements StatusListener {

    private Handler handler = new Handler();
    private Context context;
    private Activity act;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future_delivery);

        context = getApplicationContext();
        act = FutureDeliveryActivity.this;
        handler.postDelayed(runnable, 1000);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.d("polling", "run: 1 time");
            Back4app.getStatus(context, act);
            handler.postDelayed(this, 1000);
        }
    };

    @Override
    public void onStatus(String status) {
        if (Back4app.lastReceivedStatus.equals(Back4app.STATUS_0_PRESTART)) {
            Log.d("dd", "onStatus: nothing changed");
        } else if (Back4app.lastReceivedStatus.equals(Back4app.STATUS_1_STARTED)) {
            handler.removeCallbacks(runnable);
            Intent i = new Intent(context, CurrentDeliveryActivity.class);
            startActivity(i);
        } else {
            Log.d("bad", "onStatus: bad state from future delivery string");
        }
    }
}
