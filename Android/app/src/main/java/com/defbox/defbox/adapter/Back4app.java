package com.defbox.defbox.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Button;

import com.defbox.defbox.R;
import com.defbox.defbox.util.DialogBuilder;
import com.parse.FunctionCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kaiyi.lee on 9/23/17.
 */

public final class Back4app {

    public static final String STATUS_0_PRESTART = "STATUS_0_PRESTART";
    public static final String STATUS_1_STARTED = "STATUS_1_STARTED";
    public static final String STATUS_2_REACHED = "STATUS_2_REACHED";
    public static final String STATUS_3_OPENED = "STATUS_3_OPENED";
    public static final String STATUS_4_CLOSED = "STATUS_4_CLOSED";
    public static final String STATUS_5_LOCKED = "STATUS_5_LOCKED";
    public static String lastReceivedStatus = "";

    public static void login(final Context context) {
        ParseUser.logInInBackground("kaiyi", "kaiyi", new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (parseUser != null) {
                    DialogBuilder.showToast(context, parseUser.getUsername() + " has successfully logged in");
                } else {
                    DialogBuilder.showToast(context, "failed to login" + e.getMessage());
                }
            }
        });
    }

    public static void getStatus(final Context context, final Activity act) {
        Map<String, String> parameters = new HashMap<String, String>();

        ParseCloud.callFunctionInBackground("getStatus", parameters, new FunctionCallback<String>() {

            @Override
            public void done(final String status, ParseException e) {

                if (e == null) {
                    if (!lastReceivedStatus.equals(status)) {
                        lastReceivedStatus = status;
                        act.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("CHANGE", "change to update: "+ status);
                                if (status.equals(STATUS_0_PRESTART)) {
                                } else if (status.equals(STATUS_0_PRESTART)) {
                                }else if (status.equals(STATUS_1_STARTED)) {

                                }else if (status.equals(STATUS_2_REACHED)) {

                                }else if (status.equals(STATUS_3_OPENED)) {

                                }else if (status.equals(STATUS_4_CLOSED)) {

                                }else if (status.equals(STATUS_5_LOCKED)) {

                                }
                            }
                        });
                    } else {
                        Log.d("NO CHANGE", "no change to update: "+ status);
                    }

                    // DialogBuilder.showToast(context, "received object: " + status);
                }
                else {
                    DialogBuilder.showToast(context, e.toString());
                }
            }
        });
    }

    public static void postStatus(final Context context, String status) {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("status", status);

        ParseCloud.callFunctionInBackground("postStatus", parameters, new FunctionCallback<String>() {

            @Override
            public void done(String status, ParseException e) {
                if (e == null) {
                    DialogBuilder.showToast(context, "received object: " + status);
                }
                else {
                    DialogBuilder.showToast(context, e.toString());
                }
            }
        });
    }

    public static void initialize(Context context) {
        Parse.initialize(new Parse.Configuration.Builder(context)
                .applicationId("eVHV5Cmc3TtPcJJYp4wK1mjPs4zZOxcJwAokkGV3") //PASTE YOUR Back4App APPLICATION ID
                .clientKey("PYqsMqjEi2aveBF8yozobRyTLff4moi8IQaXbDFy") //PASTE YOUR CLIENT KEY
                .server("https://parseapi.Back4app.com/").build()
        );
    }


}
