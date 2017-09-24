package com.defbox.defbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.defbox.defbox.adapter.Back4app;

// only one way forward, click start delivery to post status and move forward
public class TripsListActivity extends AppCompatActivity implements StatusListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips_list);
    }

    @Override
    public void onStatus(String status) {
        // doesn't matter
    }

    // defined handler in layout xml attribute
    public void onClickStartDelivery(View v) {
        Back4app.postStatus(TripsListActivity.this, Back4app.STATUS_1_STARTED);
        startActivity(new Intent(TripsListActivity.this, TripStartedActivity.class));
    }
}
