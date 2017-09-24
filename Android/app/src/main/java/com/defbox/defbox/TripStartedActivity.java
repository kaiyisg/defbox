package com.defbox.defbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.defbox.defbox.adapter.Back4app;

public class TripStartedActivity extends EmergencyActivity implements StatusListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_started);

        Button b = findViewById(R.id.notifyArrivedButton);
        if (Back4app.lastReceivedStatus.equals(Back4app.STATUS_5_LOCKED)) {
            b.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStatus(String status) {
        // Upon clicking:
        // findViewById(R.id.notifyArrivedButton);
        // send status change ->
    }

    public void onClickNotifyArrived(View v) {
        Back4app.postStatus(this, Back4app.STATUS_2_REACHED);
        startActivity(new Intent(this, TripArrivedActivity.class));
    }
}
