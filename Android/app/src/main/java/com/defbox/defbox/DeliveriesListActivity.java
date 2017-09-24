package com.defbox.defbox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.defbox.defbox.adapter.Back4app;

import static com.defbox.defbox.adapter.Back4app.*;

public class DeliveriesListActivity extends AppCompatActivity implements StatusListener {

    private Handler handler = new Handler();
    private Context context;
    private Activity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliveries_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = getApplicationContext();
        act = DeliveriesListActivity.this;

        if (Back4app.lastReceivedStatus.equals(Back4app.STATUS_0_PRESTART)) {
            findViewById(R.id.nextDeliveryRow).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, FutureDeliveryActivity.class);
                    startActivity(i);
                }
            });
        }
    }

    @Override
    public void onStatus(String status) {
        // status 0 = direct to FutureDeliveryActivity
        // status 5 = change the first row's color from blue to @colors/colorAccent
        // else direct to CurrentDeliveryActivity
    }
}
