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

public class TripArrivedActivity extends EmergencyActivity implements StatusListener {

    private Handler handler = new Handler();
    private Context context;
    private Activity act;
    private TextView title;
    private TextView titleSec;
    private TextView titleTer;

    public static final String STATUS_2_TITLE = "Waiting for Kelly to unlock DefBox";
    public static final String STATUS_3_TITLE = "DefBox has been unlocked";
    public static final String STATUS_4_TITLE = "Signing Document...";
    public static final String STATUS_5_TITLE = "Kelly has signed!";

    public static final String STATUS_3_TITLESEC = "Please place the parcel within DefBox";
    public static final String STATUS_4_TITLESEC = "Please hold on.";
    public static final String STATUS_5_TITLESEC = "Click below to end session and start your next trip!";

    public static final String STATUS_3_TITLETER = "Tap here when done";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_arrived);
        title = findViewById(R.id.deliverTitle);
        titleSec = findViewById(R.id.deliverSec);
        titleTer = findViewById(R.id.deliverTer);

        context = getApplicationContext();
        act = TripArrivedActivity.this;
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

    public void onStatus(String status) {
        titleSec.setText("");
        titleTer.setText("");
        Log.d("dd", "onStatus: callback reached: deterministicstring");
        if (status.equals(Back4app.STATUS_0_PRESTART)) {
            Log.d("dd", "onStatus: bad state: "+ status);
        } else if (status.equals(Back4app.STATUS_1_STARTED)) {
            Log.d("dd", "onStatus: bad state: "+ status);
        } else if (status.equals(Back4app.STATUS_2_REACHED)) {
            Log.d("dd", "onStatus: no state change: "+ status);
            title.setText(STATUS_2_TITLE);
        } else if (status.equals(Back4app.STATUS_3_OPENED)) {
            Log.d("dd", "onStatus: change: "+ status);
            title.setText(STATUS_3_TITLE);
            titleSec.setText(STATUS_3_TITLESEC);
            titleTer.setText(STATUS_3_TITLETER);
            titleTer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Back4app.postStatus(context, Back4app.STATUS_4_CLOSED);
                }
            });
        } else if (status.equals(Back4app.STATUS_4_CLOSED)) {
            Log.d("dd", "onStatus: change: "+ status);
            title.setText(STATUS_4_TITLE);
            titleSec.setText(STATUS_4_TITLESEC);
        } else if (status.equals(Back4app.STATUS_5_LOCKED)) {
            Log.d("dd", "onStatus: change: "+ status);
            title.setText(STATUS_5_TITLE);
            titleSec.setText(STATUS_5_TITLESEC);
            ((TextView)findViewById(R.id.txt_currentDeliveryHeader)).setText("Click to start your next trip!");
            ConstraintLayout next = findViewById(R.id.currentDeliveryRow);
            next.setBackgroundResource(R.color.colorAccent);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, TripStartedActivity.class);
                    startActivity(i);
                }
            });
        } else {
            Log.d("bad", "onStatus: bad state is here? "+ status);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

}
