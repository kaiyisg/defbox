package com.defbox.defbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.defbox.defbox.adapter.Back4app;

public class TripStartedActivity extends AppCompatActivity implements StatusListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_started);
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
