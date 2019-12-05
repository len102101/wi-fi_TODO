package com.example.wi_fitodo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;

public class CustomDialog extends Dialog {

    interface CustomDialogListener{
        void onPositiveClicked(String name, String age, String addr);
        void onNegativeClicked();
    }

    private Button mPositiveButton;

    public void setDialogListener(CustomDialogListener customDialogListener){
        this.customDialogListener = customDialogListener;
    }


    private View.OnClickListener mPositiveListener;
    private CustomDialogListener customDialogListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //다이얼로그 밖의 화면은 흐리게 만들어줌
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.custom_dialog);

        //셋팅
        mPositiveButton = (Button)findViewById(R.id.pbutton);

        //클릭 리스너 셋팅 (클릭버튼이 동작하도록 만들어줌.)
        mPositiveButton.setOnClickListener(mPositiveListener);
    }

    //생성자 생성
    public CustomDialog(@NonNull Context context, View.OnClickListener positiveListener) {
        super(context);
    }
}
