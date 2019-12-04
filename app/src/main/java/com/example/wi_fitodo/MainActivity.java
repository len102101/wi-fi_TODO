package com.example.wi_fitodo;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    CheckBox chbox;
    TextView textView;
    CheckBox v;
    TextView contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckBox checkbox_v = findViewById(R.id.v);
        checkbox_v.setButtonDrawable(R.drawable.checkbox_v);

        chbox = (CheckBox)findViewById(R.id.chbox);
        v=(CheckBox)findViewById(R.id.v);
        contents=(TextView)findViewById(R.id.contents);

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
    }
}