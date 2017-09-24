package com.defbox.defbox.adapter;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.net.URL;

/**
 * Created by kaiyi.lee on 9/24/17.
 */

public class PollstatusThread extends Thread {
    private boolean stop = false;

    @Override public void run() {
        while (!stop) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(Message inputMessage) {

                }
            };
            // poll the USB and dispatch changes to the views with a Handler
        }
    }

    public void doStop() {
        stop = true;
    }
}
