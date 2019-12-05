package com.example.wi_fitodo;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

import androidx.annotation.RequiresApi;

public class plus_customdialog {
    private Context context;
    int mYear, mMonth, mDay;

    public plus_customdialog(Context context) {this.context = context;}

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Toast.makeText(view.getContext(), year + "년" + monthOfYear + "월" + dayOfMonth +"일", Toast.LENGTH_SHORT).show();
        }
    };

    // 호출할 다이얼로그 함수를 정의한다.
    @SuppressLint("ClickableViewAccessibility")
    public void callFunction(final TextView textView) {

        // 커스텀 다이얼로그를 정의하기위해 Dialog클래스를 생성한다.
        final Dialog dlg = new Dialog(context);

        // 액티비티의 타이틀바를 숨긴다.
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 커스텀 다이얼로그의 레이아웃을 설정한다.
        dlg.setContentView(R.layout.plus_customdialog);

        // 커스텀 다이얼로그를 노출한다.
        dlg.show();

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.
        final EditText todo = (EditText) dlg.findViewById(R.id.todo);
        final EditText contents = (EditText) dlg.findViewById(R.id.contents);
        final EditText due = (EditText) dlg.findViewById(R.id.due);
        final Button pbutton = (Button) dlg.findViewById(R.id.pbutton);
        final Button nbutton = (Button) dlg.findViewById(R.id.nbutton);

        //현재 날짜와 시간을 가져오기위한 Calendar 인스턴스 선언

        Calendar cal = new GregorianCalendar();

        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        pbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // '확인' 버튼 클릭시 메인 액티비티에서 설정한 main_label에
                // 커스텀 다이얼로그에서 입력한 메시지를 대입한다.
                textView.setText(todo.getText().toString());
                Toast.makeText(context, "\"" +  todo.getText().toString() + "\" 을 입력하였습니다.", Toast.LENGTH_SHORT).show();

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
                DatePickerDialog dia = new DatePickerDialog(v.getContext(), listener, 2019, 10, 10);
                dia.show();
            }
        });
    }
}