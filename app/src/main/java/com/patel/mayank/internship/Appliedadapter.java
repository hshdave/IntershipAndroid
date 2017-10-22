package com.patel.mayank.internship;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Harsh on 10/22/2017.
 */

public class Appliedadapter extends BaseAdapter {


    ArrayList<String> arrayList;
    Context c;
    LayoutInflater inflater;

    public Appliedadapter(ArrayList<String> arrayList, Context c) {
        this.arrayList = arrayList;
        this.c = c;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(inflater ==  null)
        {
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.spinnercontent,parent,false);
        }


        TextView textView = (TextView) convertView.findViewById(R.id.txt_spincon);

        textView.setText(arrayList.get(position).toString());

        return convertView;
    }
}
