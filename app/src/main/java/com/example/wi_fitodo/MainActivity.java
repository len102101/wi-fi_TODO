package com.example.wi_fitodo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ConnectivityManager connmanager;
    NetworkInfo netInfo;
    WifiManager wifiManager;
    WifiInfo connectionInfo;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getCurrentSsid();
    }

    public void getCurrentSsid() {
        String ssid = null;
        connmanager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        netInfo = connmanager.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            if (ConnectivityManager.TYPE_WIFI == netInfo.getType()) {
//                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//                WifiInfo info = wifiManager.getConnectionInfo();
//                ssid = info.getSSID();
//                text.setText(ssid);
            }
            }
        else {
            new AlertDialog.Builder(this).setMessage("인터넷에 연결되지 않았습니다.").setCancelable(false).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finishAffinity();
                }
            }).show();
        }
    }
}
