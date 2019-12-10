package com.example.wi_fitodo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class plus_customdialog {
    private Context context;
    int mYear, mMonth, mDay;
    private String dateText;
    private String ssid;

    private EditText todo;
    private EditText contents;
    private EditText due;
    private Button pbutton;
    private Button nbutton;



    public plus_customdialog(Context context, String ssid) {
        this.context = context;
        this.ssid = ssid;
    }

    private final DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String dateString = year + "년" + (monthOfYear + 1) + "월" + dayOfMonth + "일";
            dateText = dateString;
            due.setText(dateText);
        }
    };

    // 호출할 다이얼로그 함수를 정의한다.
    @SuppressLint("ClickableViewAccessibility")
    public void callFunction() {

        // 커스텀 다이얼로그를 정의하기위해 Dialog클래스를 생성한다.
        final Dialog dlg = new Dialog(context);

        // 액티비티의 타이틀바를 숨긴다.
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 커스텀 다이얼로그의 레이아웃을 설정한다.
        dlg.setContentView(R.layout.plus_customdialog);

        // 커스텀 다이얼로그를 노출한다.
        dlg.show();

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.
        todo = (EditText) dlg.findViewById(R.id.todo);
        contents = (EditText) dlg.findViewById(R.id.contents);
        due = (EditText) dlg.findViewById(R.id.due);
        pbutton = (Button) dlg.findViewById(R.id.pbutton);
        nbutton = (Button) dlg.findViewById(R.id.nbutton);

        //현재 날짜와 시간을 가져오기위한 Calendar 인스턴스 선언

        Calendar cal = new GregorianCalendar();

        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        pbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TodoFireBase todoData = new TodoFireBase(todo.getText().toString(), contents.getText().toString(), due.getText().toString(), false);
                todoData.insertData(ssid);

                // 커스텀 다이얼로그를 종료한다.
                dlg.dismiss();
            }
        });
        nbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "취소 했습니다.", Toast.LENGTH_SHORT).show();
                // 커스텀 다이얼로그를 종료한다.
                dlg.dismiss();
            }
        });
        due.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dia = new DatePickerDialog(v.getContext(), listener, mYear, mMonth, mDay);
                dia.show();
            }
        });
    }
}