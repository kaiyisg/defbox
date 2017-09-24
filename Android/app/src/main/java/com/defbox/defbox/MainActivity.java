package com.defbox.defbox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import com.defbox.defbox.adapter.Back4app;
import com.defbox.defbox.adapter.HyperTrackAdapter;
import com.defbox.defbox.util.DialogBuilder;
import com.hypertrack.lib.HyperTrack;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private Handler handler = new Handler();
    private Context context;
    private Activity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        context = getApplicationContext();
        act = MainActivity.this;

        Back4app.initialize(context);

        HyperTrackAdapter.initialize(this);

        final Button loginButton = findViewById(R.id.button1);
        final Button statusButton = findViewById(R.id.button2);
        final Button statusPost0Button = findViewById(R.id.button3);
        final Button statusPost1Button = findViewById(R.id.button4);
        final Button statusPost2Button = findViewById(R.id.button5);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Back4app.login(context);
            }
        });

        statusButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Back4app.getStatus(context, act);
            }
        });

        statusPost0Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Back4app.postStatus(context, Back4app.STATUS_0_PRESTART);
            }
        });
        statusPost1Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Back4app.postStatus(context, Back4app.STATUS_1_STARTED);
            }
        });
        statusPost2Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Back4app.postStatus(context, Back4app.STATUS_2_REACHED);
            }
        });

        HyperTrackAdapter.checkForLocationSettings();


        handler.postDelayed(runnable, 100);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.d("miao", "run: 1 time");
            Back4app.getStatus(context, act);
            handler.postDelayed(this, 1000);
        }
    };

    protected void onPause() {
        super.onPause();
        HyperTrackAdapter.stop();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_receiver) {
            Intent myIntent = new Intent(MainActivity.this, DeliveriesListActivity.class);
            startActivity(myIntent);
        } else if (id == R.id.nav_deliverer) {
            Intent myIntent = new Intent(MainActivity.this, DelivererActivity.class);
            startActivity(myIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
