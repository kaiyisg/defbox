package com.defbox.defbox;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.defbox.defbox.adapter.Back4app;

public class EmergencyActivity extends AppCompatActivity {
    public void emergency(View v) {
        Back4app.postStatus(this, Back4app.STATUS_0_PRESTART);
        Intent ahh = new Intent(this, MainActivity.class);
        ahh.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(ahh);
    }
}
