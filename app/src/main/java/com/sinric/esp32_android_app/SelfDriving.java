package com.sinric.esp32_android_app;

import android.util.Log;

import java.util.concurrent.TimeUnit;

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
        Log.i("alex", String.valueOf(value.getBytes()));
        activity.btSendBytes(value.getBytes());
    }

    public void start(MainActivity mainActivity){
        Log.i("selfdriving", "starting..");
        activity = mainActivity;
        sendStringToESP32( "7");
        sendStringToESP32( "1");
        sendStringToESP32( "5");

    }
}
