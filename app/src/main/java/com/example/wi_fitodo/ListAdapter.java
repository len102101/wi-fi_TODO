package com.example.wi_fitodo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    LayoutInflater inflater = null;
    private ArrayList<ItemData> m_oData = null;
    private int nListCnt = 0;

    public ListAdapter(ArrayList<ItemData> _oData)
    {
        m_oData = _oData;
        nListCnt = m_oData.size();
    }

    @Override
    public int getCount()
    {
        Log.i("TAG", "getCount");
        return nListCnt;
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            final Context context = parent.getContext();
            if (inflater == null)
            {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        TextView otexttodo = (TextView) convertView.findViewById(R.id.texttodo);
        TextView otextcontents = (TextView) convertView.findViewById(R.id.textcontents);
        TextView otextdue = (TextView) convertView.findViewById(R.id.textdue);
        TextView otextpk = (TextView) convertView.findViewById(R.id.pkValue);
        CheckBox ochbox = (CheckBox) convertView.findViewById(R.id.chbox);
        CheckBox ovchbox = (CheckBox) convertView.findViewById(R.id.v);
        LinearLayout olistlayout=(LinearLayout)convertView.findViewById(R.id.list) ;

        otexttodo.setText(m_oData.get(position).strtodo);
        otextcontents.setText(m_oData.get(position).strcontents);
        otextdue.setText(m_oData.get(position).strdue);
        otextpk.setText(m_oData.get(position).strpk);


        ochbox.setTag("1");
        ovchbox.setTag("2");
        olistlayout.setTag("3");

        ochbox.setOnClickListener(m_oData.get(position).onClickListener);
        ovchbox.setOnClickListener(m_oData.get(position).onClickListener);
        olistlayout.setOnClickListener(m_oData.get(position).onClickListener);

        convertView.setTag(""+position);
        return convertView;
    }
}