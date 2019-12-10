package com.example.wi_fitodo;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;

public class listview_mainactivity extends AppCompatActivity {
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
        for (int i=0; i<1000; ++i)
        {
            ItemData oItem = new ItemData();
            oItem.strtodo = "리스트 " + (i+1);
            oItem.strcontents="세부내용 "+(i+1);
            oItem.strdue = strDate[nDatCnt++];
            oData.add(oItem);
            if (nDatCnt >= strDate.length) nDatCnt = 0;
        }

// ListView, Adapter 생성 및 연결 ------------------------
//        m_oListView = (ListView)findViewById(R.id.listView);
//        ListAdapter oAdapter = new ListAdapter(oData);
//        m_oListView.setAdapter(oAdapter);
    }
}
