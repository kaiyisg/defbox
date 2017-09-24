package com.defbox.defbox.util;

import android.content.Context;
import android.widget.Toast;


/**
 * Created by kaiyi.lee on 9/23/17.
 */

public final class DialogBuilder {

    public static void showToast(Context context, String message){
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }
}
