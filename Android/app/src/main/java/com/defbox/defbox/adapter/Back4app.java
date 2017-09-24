package com.defbox.defbox.adapter;

import android.content.Context;

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

    public static void startDelivery() {
        Map<String, String> parameters = new HashMap<String, String>();

        ParseCloud.callFunctionInBackground("test", parameters, new FunctionCallback<Map<String, Object>>() {

            @Override
            public void done(Map<String, Object> mapObject, ParseException e) {
                if (e == null) {
                    // Everything went alright
                }
                else {
                    // Something went wrong
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
