package com.example.wi_fitodo;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;

public class listview_mainactivity extends AppCompatActivity implements View.OnClickListener{
    private ListView m_oListView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// 데이터 1000개 생성--------------------------------.
        String[] strDate = {"2017-01-03", "1965-02-23", "2016-04-13", "2010-01-01", "2017-06-20",
                "2012-07-08", "1980-04-14", "2016-09-26", "2014-10-11", "2010-12-24"};
        int nDatCnt=0;
        ArrayList<ItemData> oData = new ArrayList<>();
        for (int i=0; i<100; ++i)
        {
            ItemData oItem = new ItemData();
            oItem.strtodo = "리스트 " + (i+1);
            oItem.strcontents="세부내용 "+(i+1);
            oItem.onClickListener = this;
            oItem.strdue = strDate[nDatCnt++];
            oData.add(oItem);
            if (nDatCnt >= strDate.length) nDatCnt = 0;
        }

// ListView, Adapter 생성 및 연결 ------------------------
        m_oListView = (ListView)findViewById(R.id.listView);
        ListAdapter oAdapter = new ListAdapter(oData);
        m_oListView.setAdapter(oAdapter);
    }

    public void onClick(View v){
        int nViewTag = Integer.parseInt((String)v.getTag());
//        String strViewName = "";
        View oParentView = (View)v.getParent();
        switch (nViewTag)
        {
            case 1: // ochbox
                CheckBox chbox = (CheckBox)oParentView.findViewById(R.id.chbox);
                TextView texttodo = (TextView)oParentView.findViewById(R.id.texttodo);
                if(chbox.isChecked()) {    //체크 박스가 체크 된 경우
                    texttodo.setPaintFlags(texttodo.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                    texttodo.setTextColor(Color.parseColor("#999999"));
                }
                else {   //체크 박스가 해제 된 경우
                    texttodo.setPaintFlags(0);
                    texttodo.setTextColor(Color.parseColor("#353535"));
                }
                break;
            case 2: // ovchbox
                View oPParentView = (View)v.getParent().getParent();
                CheckBox chboxv = (CheckBox)oParentView.findViewById(R.id.v);
                TextView contents = (TextView)oPParentView.findViewById(R.id.textcontents);
                if(chboxv.isChecked()) {    //체크 박스가 체크 된 경우
                    contents.setVisibility(View.VISIBLE);
                }
                else {   //체크 박스가 해제 된 경우
                    contents.setVisibility(View.GONE);
                }
                break;
        }
    }
}