package com.sinric.esp32_android_app;

import android.os.StrictMode;
import android.util.Log;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class InfluxDBWrites {
    // https://docs.influxdata.com/influxdb/cloud/api/#operation/PostWrite

    private static String token = "XKGbhSKhGFiGATUD9rk9GphKjvJOsbdJSxbAJE8Al24oHN4GLSIcTpYYnV2VZ_5YgWc_094i1E6q5vKxAmT4hQ==";
    private static String org = "5dbd651117628225";
    private static String bucket = "alessandro.solbiati%27s%20Bucket";
    private static String url = "https://westeurope-1.azure.cloud2.influxdata.com/api/v2/write?org=5dbd651117628225&bucket=alessandro.solbiati%27s%20Bucket&precision=s";

    public static final MediaType MEDIA_TYPE_PLAIN
            = MediaType.parse("text/plain; charset=utf-8");


    public static void HTTPwrite() {
        // TODO: https://stackoverflow.com/questions/6343166/how-to-fix-android-os-networkonmainthreadexception#_=_
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        OkHttpClient client = new OkHttpClient();
        long unixTime = System.currentTimeMillis() / 1000L;
        String postBody = ""
                + "charge,host=host1 used_percent=23.43234543 "+String.valueOf(unixTime)+"\n";


        Request request;
        request = new Request.Builder()
                .url(url)
                .header("Authorization", "Token XKGbhSKhGFiGATUD9rk9GphKjvJOsbdJSxbAJE8Al24oHN4GLSIcTpYYnV2VZ_5YgWc_094i1E6q5vKxAmT4hQ==")
                .post(RequestBody.create(MEDIA_TYPE_PLAIN, postBody))
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println(response.body().string());
        } catch (Exception e) {
            Log.i("influxdb", String.valueOf(e));
        }
    }
}