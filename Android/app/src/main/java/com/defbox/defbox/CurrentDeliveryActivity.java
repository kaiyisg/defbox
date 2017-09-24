package com.defbox.defbox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.defbox.defbox.adapter.Back4app;

public class CurrentDeliveryActivity extends AppCompatActivity implements StatusListener {

    private Handler handler = new Handler();
    private Context context;
    private Activity act;
    private TextView addressText;
    private TextView titleText;
    private ConstraintLayout state_1;
    private ConstraintLayout state_2;
    private ConstraintLayout state_3;
    private ConstraintLayout state_4;
    private ConstraintLayout state_5;


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
        state_1.findViewById(R.id.row_1);
        state_2.findViewById(R.id.row_2);
        state_3.findViewById(R.id.row_3);
        state_4.findViewById(R.id.row_4);
        state_5.findViewById(R.id.row_5);


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
        Log.d("dd", "onStatus: callback reached: deterministicstring");
        if (status.equals(Back4app.STATUS_0_PRESTART)) {
            Log.d("dd", "onStatus: bad state: "+ status);
        } else if (status.equals(Back4app.STATUS_1_STARTED)) {
            state_1.setVisibility(View.VISIBLE);
            state_2.setVisibility(View.GONE);
            state_3.setVisibility(View.GONE);
            state_4.setVisibility(View.GONE);
            state_5.setVisibility(View.GONE);
            titleText.setText(STATUS_1_ADDRESS);
            Log.d("dd", "onStatus: no state change: "+ status);
        } else if (status.equals(Back4app.STATUS_2_REACHED)) {
            state_1.setVisibility(View.GONE);
            state_2.setVisibility(View.VISIBLE);
            state_3.setVisibility(View.GONE);
            state_4.setVisibility(View.GONE);
            state_5.setVisibility(View.GONE);
            titleText.setText(STATUS_2_ADDRESS);
            Log.d("dd", "onStatus: change "+ status);
        } else if (status.equals(Back4app.STATUS_3_OPENED)) {
            state_1.setVisibility(View.GONE);
            state_2.setVisibility(View.GONE);
            state_3.setVisibility(View.VISIBLE);
            state_4.setVisibility(View.GONE);
            state_5.setVisibility(View.GONE);
            titleText.setText(STATUS_3_ADDRESS);
            Log.d("dd", "onStatus: change: "+ status);
        } else if (status.equals(Back4app.STATUS_4_CLOSED)) {
            state_1.setVisibility(View.GONE);
            state_2.setVisibility(View.GONE);
            state_3.setVisibility(View.GONE);
            state_4.setVisibility(View.VISIBLE);
            state_5.setVisibility(View.GONE);
            titleText.setText(STATUS_4_ADDRESS);
            Log.d("dd", "onStatus: change: "+ status);
        } else if (status.equals(Back4app.STATUS_5_LOCKED)) {
            state_1.setVisibility(View.GONE);
            state_2.setVisibility(View.GONE);
            state_3.setVisibility(View.GONE);
            state_4.setVisibility(View.GONE);
            state_5.setVisibility(View.VISIBLE);
            titleText.setText(STATUS_5_ADDRESS);
            Log.d("dd", "onStatus: change: "+ status);
        } else {
            Log.d("bad", "onStatus: bad state is here? "+ status);
        }
    }
}
