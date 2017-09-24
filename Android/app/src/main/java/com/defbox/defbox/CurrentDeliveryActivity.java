package com.defbox.defbox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.defbox.defbox.adapter.Back4app;

public class CurrentDeliveryActivity extends AppCompatActivity implements StatusListener {

    private Handler handler = new Handler();
    private Context context;
    private Activity act;
    private TextView addressText;
    private TextView titleText;

    public static final String STATUS_1_TITLE = "Your package is arriving";
    public static final String STATUS_2_TITLE = "Your package has arrived";
    public static final String STATUS_3_TITLE = "DefBox is opened";
    public static final String STATUS_4_TITLE = "DefBox is closed";
    public static final String STATUS_5_TITLE = "DefBox is now locked";


    public static final String STATUS_1_ADDRESS = "Palo Alto";
    public static final String STATUS_2_ADDRESS = "12 Hillsdale Drive";
    public static final String STATUS_3_ADDRESS = "12 Hillsdale Drive";
    public static final String STATUS_4_ADDRESS = "12 Hillsdale Drive";
    public static final String STATUS_5_ADDRESS = "12 Hillsdale Drive";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_delivery);

        addressText = findViewById(R.id.textView19);
        titleText = findViewById(R.id.txt_currentDeliveryHeader);


        context = getApplicationContext();
        act = CurrentDeliveryActivity.this;
        handler.postDelayed(runnable, 100);
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
        if (status.equals(Back4app.STATUS_0_PRESTART)) {
            Log.d("dd", "onStatus: bad state: "+ status);
        } else if (status.equals(Back4app.STATUS_1_STARTED)) {
            addressText.setText(STATUS_1_TITLE);
            titleText.setText(STATUS_1_ADDRESS);
            Log.d("dd", "onStatus: no state change: "+ status);
        } else if (status.equals(Back4app.STATUS_2_REACHED)) {
            addressText.setText(STATUS_2_TITLE);
            titleText.setText(STATUS_2_ADDRESS);
            Log.d("dd", "onStatus: change "+ status);
        } else if (status.equals(Back4app.STATUS_3_OPENED)) {
            addressText.setText(STATUS_3_TITLE);
            titleText.setText(STATUS_3_ADDRESS);
            Log.d("dd", "onStatus: change: "+ status);
        } else if (status.equals(Back4app.STATUS_4_CLOSED)) {
            addressText.setText(STATUS_4_TITLE);
            titleText.setText(STATUS_4_ADDRESS);
            Log.d("dd", "onStatus: change: "+ status);
        } else if (status.equals(Back4app.STATUS_5_LOCKED)) {
            addressText.setText(STATUS_5_TITLE);
            titleText.setText(STATUS_5_ADDRESS);
            Log.d("dd", "onStatus: change: "+ status);
        } else {
            Log.d("bad", "onStatus: bad state");
        }
    }
}
