package com.hp.technicalfest;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class DialogActivity extends AppCompatActivity {
    TextView tv,tv1,tv2,tv3,tv4,tv5,tv6;
    String wid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        tv= (TextView) findViewById(R.id.dwid);
        tv1= (TextView) findViewById(R.id.dwname);
        tv2= (TextView) findViewById(R.id.dwtype);
        tv3= (TextView) findViewById(R.id.dwvenue);
        tv4= (TextView) findViewById(R.id.dwtime);
        tv5= (TextView) findViewById(R.id.dwprice);
        tv6=(TextView)findViewById(R.id.dwstatus);

        Intent intent=getIntent();
        String val=intent.getStringExtra("name");
        try{

            SQLiteDatabase sd = openOrCreateDatabase("sample", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            String []params={val};
            Cursor c=sd.rawQuery("SELECT * FROM workshop where workshopname=?",params);
          c.moveToFirst();
            wid=c.getString(0);
            tv.setText(c.getString(0));
            tv1.setText(c.getString(1));
            tv2.setText(c.getString(2));
            tv3.setText(c.getString(3));
            tv4.setText(c.getString(4));
            tv5.setText(c.getString(5));



        }catch (SQLiteException e){

        }
        try {

            SQLiteDatabase sd1 = openOrCreateDatabase("coordinatorhelper", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            String[] params = {wid};
            Cursor c1 = sd1.rawQuery("SELECT * FROM coordinator where wid=?", params);
            c1.moveToFirst();
           int status=Integer.parseInt(c1.getString(4));
            if(status==1){
                tv6.setText("Event Open");
            }else{
                tv6.setText("Event Closed");
            }
        }catch (SQLiteException e){

        }
    }
}
