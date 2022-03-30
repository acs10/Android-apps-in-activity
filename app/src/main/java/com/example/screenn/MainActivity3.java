package com.example.screenn;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    private TextView batteryTxt;
    private TextView chargeTime;
    public long timeRemaning;

    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            int level = intent.getIntExtra( BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            float batteryPct = level * 100 / (float)scale;
            batteryTxt.setText(String.valueOf(batteryPct) + "%");
        }
    };

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity3);
        batteryTxt = (TextView) this.findViewById(R.id.batteryTxt);
        chargeTime = (TextView) this.findViewById(R.id.chargeTime);
        BatteryManager bateryM = (BatteryManager) getApplicationContext().getSystemService(Context.BATTERY_SERVICE);
        this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            timeRemaning = bateryM.computeChargeTimeRemaining();
            chargeTime.setText("Falta Carregar: "+String.valueOf( timeRemaning));
        }
    }
}
