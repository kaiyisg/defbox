package com.defbox.defbox;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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

        final Context context = getApplicationContext();

        Back4app.initialize(context);
        Back4app.installPush();

        // HyperTrackAdapter.initialize(context);

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
                Back4app.getStatus(context);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_receiver) {
            Intent myIntent = new Intent(MainActivity.this, ReceiverActivity.class);
            startActivity(myIntent);
        } else if (id == R.id.nav_deliverer) {
            Intent myIntent = new Intent(MainActivity.this, DelivererActivity.class);
            startActivity(myIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
