package com.example.changenumber;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class ChangeNumberService extends Service {
    private String numberStr;
    private final IBinder binder = new LocalBinder();
    public ChangeNumberService() {
    }
    public class LocalBinder extends Binder {
        ChangeNumberService getService() {
            // Return this instance of LocalService so clients can call public methods
            return ChangeNumberService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        numberStr = "0";
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void setNumberStr(String nStr){
        numberStr = nStr;
    }
    public String getNumberStr(){
        return numberStr;
    }
}