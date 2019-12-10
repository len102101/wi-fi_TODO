package com.example.wi_fitodo;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    CheckBox chbox;
    TextView textView;
    CheckBox v;
    TextView contents;
    LinearLayout list1;
    ImageButton plus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckBox checkbox_v = findViewById(R.id.v);
        checkbox_v.setButtonDrawable(R.drawable.checkbox_v);

        chbox = (CheckBox)findViewById(R.id.chbox);
        v=(CheckBox)findViewById(R.id.v);
        contents=(TextView)findViewById(R.id.contents);
        plus=(ImageButton)findViewById(R.id.plus);

        list1 = (LinearLayout)findViewById(R.id.list1);

        chbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                textView = (TextView)findViewById(R.id.textview);
                if(chbox.isChecked()) {    //체크 박스가 체크 된 경우
                    textView.setPaintFlags(textView.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                    textView.setTextColor(Color.parseColor("#999999"));
                }
                else {   //체크 박스가 해제 된 경우
                    textView.setPaintFlags(0);
                    textView.setTextColor(Color.parseColor("#353535"));
                }
            }
        });

        v.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(v.isChecked()) {    //체크 박스가 체크 된 경우
                    contents.setVisibility(View.VISIBLE);
                }
                else {   //체크 박스가 해제 된 경우
                    contents.setVisibility(View.GONE);
                }
            }
        });

        list1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(MainActivity.this);
                alert_confirm.setMessage("What do you want?").setPositiveButton("Update",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 'YES'
                            }
                        }).setNegativeButton("Delete",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 'No'
                                list1.setVisibility(View.GONE);
                                return;
                            }
                        });
                AlertDialog alert = alert_confirm.create();
                alert.show();

                // AlertDialog 에서 위치 크기 수정

                WindowManager.LayoutParams params = alert.getWindow().getAttributes();
                params.width = 500;
                alert.getWindow().setAttributes(params);

                Button btn = alert.getButton(AlertDialog.BUTTON_POSITIVE);
                btn.setGravity(Gravity.LEFT);

                return false;
            }
        });


        plus.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                 // 커스텀 다이얼로그를 생성한다. 사용자가 만든 클래스이다.
                plus_customdialog customDialog = new plus_customdialog(MainActivity.this);

                // 커스텀 다이얼로그를 호출한다.
                // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
                customDialog.callFunction(textView);
            }
        });
    }
}