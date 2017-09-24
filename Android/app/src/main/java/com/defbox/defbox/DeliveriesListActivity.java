package com.defbox.defbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import static com.defbox.defbox.adapter.Back4app.*;

public class DeliveriesListActivity extends AppCompatActivity implements StatusListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliveries_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViewById(R.id.nextDeliveryRow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // do smth?
            }
        });

    }


    @Override
    public void onStatus(String status) {
        // status 0 = direct to FutureDeliveryActivity
        // status 5 = change the first row's color from blue to @colors/colorAccent
        // else direct to CurrentDeliveryActivity
    }
}
