package com.patel.mayank.internship;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Testactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testactivity);

        Intent i = getIntent();
        String nm =  i.getStringExtra("nam");
        String uid = i.getStringExtra("uid");

        TextView txtv = (TextView) findViewById(R.id.txt_nm);
        TextView txtuid = (TextView) findViewById(R.id.txt_uid);
        txtv.setText(nm);
        txtuid.setText(uid);
    }
}
