package com.defbox.defbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TripsListActivity extends AppCompatActivity implements StatusListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips_list);
    }

    @Override
    public void onStatus(String status) {
        // regardless of status, once click start delivery
        findViewById(R.id.nextDeliveryRow);
    }
}
