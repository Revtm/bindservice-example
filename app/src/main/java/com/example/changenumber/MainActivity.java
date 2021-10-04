package com.example.changenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ChangeNumberService change = null;
    private boolean mBound = false;
    private Intent intent;
    private EditText numberStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberStr = (EditText) findViewById(R.id.editNumber);
    }

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            ChangeNumberService.LocalBinder binder = (ChangeNumberService.LocalBinder) service;
            change = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    public void setNumber(View view) {
        if(mBound){
            change.setNumberStr(numberStr.getText().toString());
        }else{
            Toast.makeText(this, "Service is Null ", Toast.LENGTH_SHORT).show();
        }
    }

    public void showNumber(View view){
        if(mBound){
            String num = change.getNumberStr();
            Toast.makeText(this, "number: " + num, Toast.LENGTH_SHORT).show();
        }
    }

    public void startBinding(View view){
        if(!mBound){
            intent = new Intent(this, ChangeNumberService.class);
            bindService(intent, connection, Context.BIND_AUTO_CREATE);
        }
    }

    public void stopBinding(View view){
        if(mBound){
            unbindService(connection);
            mBound = false;
        }
    }
}