package com.example.wi_fitodo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int LOCATION = 1;
    private TextView text;
    private Button btn1;
    private CustomDialog customDialog;

    private View.OnClickListener positiveListener = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "확인버튼이 눌렸습니다.",Toast.LENGTH_SHORT).show();
            customDialog.dismiss();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tryToReadSSID();
        btn1 = (Button) findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDialog(view);
            }
        });
    }

    private void tryToReadSSID() {
        text = (TextView) findViewById(R.id.text);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }else{//Permission already granted
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if(wifiInfo.getSupplicantState() == SupplicantState.COMPLETED){
                String ssid = wifiInfo.getSSID();
                text.setText(ssid);
            }
        }
    }

    private void inputDialog(View view){

        final Button btn = new Button(view.getContext());
        btn.setText("Date Pick");

        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                customDialog = new CustomDialog(view.getContext(), positiveListener);
                customDialog.show();
            }
        });

        final AlertDialog.Builder alt_bld = new AlertDialog.Builder(view.getContext());
        final EditText et1 = new EditText(view.getContext());
        final EditText et2 = new EditText(view.getContext());
        alt_bld.setTitle("TODO")
                .setCancelable(false)
                .setView(et1)
                .setView(et2)
                .setView(btn)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String str = et1.getText().toString() + et2.getText().toString();
                        Toast.makeText(getApplicationContext(),str, Toast.LENGTH_SHORT).show();
                    }
                });

        AlertDialog alert = alt_bld.create();

        alert.show();
    }
}