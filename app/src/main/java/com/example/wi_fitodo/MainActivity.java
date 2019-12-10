package com.example.wi_fitodo;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageButton plus;
    private ListView m_oListView = null;
    private String ssid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plus = (ImageButton)findViewById(R.id.plus);
        this.ssid = getSSID();

        setTodoList();
        String a = FirebaseDatabase.getInstance().getReference().toString() + " END";
        Toast.makeText(this, a, Toast.LENGTH_LONG);

        plus.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                // 커스텀 다이얼로그를 생성한다. 사용자가 만든 클래스이다.
                plus_customdialog customDialog = new plus_customdialog(MainActivity.this, ssid);

                // 커스텀 다이얼로그를 호출한다.
                // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
                customDialog.callFunction();
            }
        });
    }

    private void setTodoList(){
        FirebaseDatabase.getInstance().getReference().child("todos").child(ssid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<ItemData> oData = new ArrayList<>();
                Log.d("MainActivity", "CallValue : ");
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Log.d("MainActivity", "ValueEventListener : " + snapshot.getKey());

                    ItemData oItem = new ItemData();
                    oItem.strpk = snapshot.getKey();
                    oItem.strtodo = snapshot.child("title").getValue().toString();
                    oItem.strcontents = snapshot.child("context").getValue().toString();
                    oItem.strdue = snapshot.child("date").getValue().toString();
                    oData.add(oItem);
                }
                m_oListView = (ListView)findViewById(R.id.listView);
                ListAdapter oAdapter = new ListAdapter(oData);
                m_oListView.setAdapter(oAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED && requestCode == 1){
            //User allowed the location and you can read it now
            getSSID();
        }
    }

    private String getSSID() {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if(wifiInfo.getSupplicantState() == SupplicantState.COMPLETED){
            String ssid = wifiInfo.getSSID();
            // Toast.makeText(this, ssid, Toast.LENGTH_LONG).show();
            return ssid;
        }
        return "no internet";
    }
}