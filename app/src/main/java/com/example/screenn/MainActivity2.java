package com.example.screenn;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        PackageManager packageManager=getPackageManager();
        List<ApplicationInfo> list = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

        List<String> values = new ArrayList<String>(0);
        for(ApplicationInfo ap:list){
            values.add(ap.packageName);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                if (ap.enabled){
                    values.add("Enabled");
                } else {
                    values.add("Disabled");
                }
            }
        }
        ListView lista =  ListView.class.cast(findViewById(R.id.lista));
        lista.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values));
        System.out.println(lista);
    }
}