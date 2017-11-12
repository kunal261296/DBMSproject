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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class temp extends AppCompatActivity {
    ListView lv;
    CoreCommiteeDbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        lv=(ListView)findViewById(R.id.listview);
        db=new CoreCommiteeDbHelper(this);
        ArrayList<String> template=new ArrayList<>();


        /*if(data.getCount()==0) {
            Toast.makeText(temp.this, "databse was empty", Toast.LENGTH_SHORT).show();


        }else{
            while(data.moveToNext()){
                arrayList.add(data.getString(0));
                ListAdapter listadapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
                lv.setAdapter(listadapter);
            }
        }*/
        try{

            SQLiteDatabase sd = openOrCreateDatabase("sample", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            Cursor c=sd.rawQuery("SELECT * FROM workshop",null);
            c.moveToFirst();
            while(!c.isAfterLast()){

                String s1=c.getString(1);

                template.add(s1);
                c.moveToNext();
            }


           ListAdapter list=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,template);
           lv.setAdapter(list);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent=new Intent(getApplicationContext(),DialogActivity.class);
                    intent.putExtra("name",lv.getItemAtPosition(position).toString());
                    startActivity(intent);
                }
            });
        }catch (SQLiteException e){

        }
    }
}
