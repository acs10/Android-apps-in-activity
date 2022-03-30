package com.example.screenn;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private MyBroadcastReceiver receiver;

    public boolean checkWifiOnAndConnected() {
        WifiManager wifiMgr = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiMgr.isWifiEnabled()) { // Wi-Fi adapter is ON
            return true; // Connected to an access point
        }
        else {
            return false; // Wi-Fi adapter is OFF
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        Intent intent = new Intent(this, MyIntentService.class);
        Intent intent2 = new Intent(this, MyIntentService.class);
        intent.putExtra("wifi", "Enabled");
        intent2.putExtra("wifi2", "Disabled");

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        String name = intent.getStringExtra("wifi");
        String name2 = intent2.getStringExtra("wifi2");
        button1.setOnClickListener(
                v -> {
                    if (checkWifiOnAndConnected() == false) {
                        Toast.makeText(this, name2, Toast.LENGTH_LONG).show();
                    }
                    else if (checkWifiOnAndConnected() == true) {
                        Toast.makeText(this, name, Toast.LENGTH_LONG).show();
                    }
                }
        );
        button2.setOnClickListener(
                v -> startActivity(new Intent(this, MainActivity2.class))
        );
        button3.setOnClickListener(
                v -> startActivity(new Intent(this, MainActivity3.class))
        );

    }
    @Override
    protected void onStart() {
        super.onStart();
        receiver = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(receiver, filter );
    }
    @Override
    protected void onStop() {
        super.onStop();
        receiver = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(receiver, filter);
    }

    class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            IntentFilter filter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
            Log.d("ESS", "WiFi mudou!");
        }
    }
}