package com.defbox.defbox;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by kaiyi.lee on 9/24/17.
 */

public class HttpConnector {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public static OkHttpClient client = new OkHttpClient();

    public static void post(String url, String json) throws IOException {
        RequestBody reqbody = RequestBody.create(null, new byte[0]);
//        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(reqbody)
                .build();
        client.newCall(request).enqueue(new Callback() {
                                            @Override
                                            public void onFailure(Call call, IOException e) {
                                                e.printStackTrace();
                                            }

                                            @Override
                                            public void onResponse(Call call, final Response response) throws IOException {
                                                if (!response.isSuccessful()) {
                                                    throw new IOException("Unexpected code " + response);
                                                } else {
                                                    Log.d("", "onResponse: ");
                                                }
                                                }
                                            });
                                        };
//        Response response = client.newCall(request).execute();
//        return response.body().string();

}


