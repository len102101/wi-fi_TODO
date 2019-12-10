package com.example.wi_fitodo;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// 데이터 1000개 생성--------------------------------.
        String[] strDate = {"2017-01-03", "1965-02-23", "2016-04-13", "2010-01-01", "2017-06-20",
                "2012-07-08", "1980-04-14", "2016-09-26", "2014-10-11", "2010-12-24"};
        int nDatCnt=0;
        final ArrayList<ItemData> oData = new ArrayList<>();
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
        ListView m_oListView;
        m_oListView = (ListView)findViewById(R.id.listView);
        ListAdapter oAdapter = new ListAdapter(oData);
        m_oListView.setAdapter(oAdapter);
    }

    public void onClick(View v){
        int nViewTag = Integer.parseInt((String)v.getTag());
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
            case 3:
//                Toast.makeText(getApplicationContext(),"착즙착즙",Toast.LENGTH_LONG).show();
                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(MainActivity.this);
                alert_confirm.setMessage("What do you want?").setPositiveButton("Update",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 'YES'
                                Toast.makeText(getApplicationContext(),"YES!",Toast.LENGTH_LONG).show();
                            }
                        }).setNegativeButton("Delete",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 'No'
                                Toast.makeText(getApplicationContext(),"노논",Toast.LENGTH_LONG).show();
//                                list1.setVisibility(View.GONE);
                                return;
                            }
                        });
                AlertDialog alert = alert_confirm.create();
                alert.show();
                break;
        }
    }
}
