package com.defbox.defbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
}
