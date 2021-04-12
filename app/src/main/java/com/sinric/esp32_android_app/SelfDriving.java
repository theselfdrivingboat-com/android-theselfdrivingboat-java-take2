package com.sinric.esp32_android_app;

import android.util.Log;

import java.security.Key;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class SelfDriving {

    MainActivity activity;

    private void sendStringToESP32(String value){
        Log.i("alex", "sleeping 1");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("alex", value);
        Log.i("alex", Arrays.toString(value.getBytes()));
        activity.btSendBytes(value.getBytes());
    }

    private void heroku_readLastCommand(){
        // Initialize a new RequestQueue instance
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(activity.getApplicationContext());

        String url = "https://theselfdrivingboat.herokuapp.com/read_last_command?boat_name=5kgboat-001";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("selfdriving", String.valueOf(response));
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        // Access the RequestQueue through your singleton class.
        requestQueue.add(jsonObjectRequest);
    }

    private void testDrive(){
        sendStringToESP32( "7");
        sendStringToESP32( "1");
        sendStringToESP32( "5");
    }

    public void start(MainActivity mainActivity){
        Log.i("selfdriving", "starting..");
        activity = mainActivity;
        heroku_readLastCommand();
    }
}
