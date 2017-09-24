package com.defbox.defbox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;

import com.defbox.defbox.adapter.Back4app;
import com.defbox.defbox.adapter.HyperTrackAdapter;
import com.defbox.defbox.util.DialogBuilder;
import com.hypertrack.lib.HyperTrack;

public class MainActivity extends AppCompatActivity {


    private Handler handler = new Handler();
    private Context context;
    private Activity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        act = MainActivity.this;

        Back4app.initialize(context);

//        HyperTrackAdapter.initialize(this);

        ImageButton receiverButton = findViewById(R.id.imageButton2);
        ImageButton delivererButton = findViewById(R.id.imageButton);

        receiverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Back4app.postStatus(context, Back4app.STATUS_0_PRESTART);
                Back4app.lastReceivedStatus = Back4app.STATUS_0_PRESTART;
                Intent i = new Intent(context, DeliveriesListActivity.class);
                startActivity(i);
            }
        });

        delivererButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Back4app.postStatus(context, Back4app.STATUS_0_PRESTART);
                Back4app.lastReceivedStatus = Back4app.STATUS_0_PRESTART;
                Intent i = new Intent(context, DelivererActivity.class);
                startActivity(i);
            }
        });

//        final Button loginButton = findViewById(R.id.button1);
//        final Button statusButton = findViewById(R.id.button2);
//        final Button statusPost0Button = findViewById(R.id.button3);
//        final Button statusPost1Button = findViewById(R.id.button4);
//        final Button statusPost2Button = findViewById(R.id.button5);
//
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Back4app.login(context);
//            }
//        });
//
//        statusButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Back4app.getStatus(context, act);
//            }
//        });
//
//        statusPost0Button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Back4app.postStatus(context, Back4app.STATUS_0_PRESTART);
//            }
//        });
//        statusPost1Button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Back4app.postStatus(context, Back4app.STATUS_1_STARTED);
//            }
//        });
//        statusPost2Button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Back4app.postStatus(context, Back4app.STATUS_2_REACHED);
//            }
//        });

//        HyperTrackAdapter.checkForLocationSettings();


//        handler.postDelayed(runnable, 100);
    }

//    private Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            Log.d("miao", "run: 1 time");
//            Back4app.getStatus(context, act);
//            handler.postDelayed(this, 1000);
//        }
//    };

    protected void onPause() {
        super.onPause();
//        HyperTrackAdapter.stop();
    }

    /**
         * Handle on Grant Location Permissions request accepted/denied result
         * @param requestCode
         * @param permissions
         * @param grantResults
         */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions,
                grantResults);

        if (requestCode == HyperTrack.REQUEST_CODE_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0]
                    == PackageManager.PERMISSION_GRANTED) {
                // Check if Location Settings are enabled to proceed
                HyperTrackAdapter.checkForLocationSettings();

            } else {
                // Handle Location Permission denied error
                DialogBuilder.showToast(this, "enabled location services request denied");
            }
        }
    }


    /**
     * Handle on Enable Location Services request accepted/denied result
     * @param requestCode
     * @param resultCode
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == HyperTrack.REQUEST_CODE_LOCATION_SERVICES) {
            if (resultCode == Activity.RESULT_OK) {
                // Check if Location Settings are enabled to proceed
                HyperTrackAdapter.checkForLocationSettings();
            } else {
                // Handle Enable Location Services request denied error
                DialogBuilder.showToast(this, "enabled location services request denied");
            }
        }
    }

}
