package com.hp.technicalfest;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoordiantorViewParticipants extends AppCompatActivity {
String cid;
    GridView gv;
String wid;
    Context ctx=this;

    ArrayList<String> usn=new ArrayList<String>();
    ArrayList<String> students=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordiantor_view_participants);
        gv= (GridView) findViewById(R.id.gridview);
        Intent i=getIntent();
        cid=i.getStringExtra("w");
        Toast.makeText(CoordiantorViewParticipants.this,cid,Toast.LENGTH_SHORT).show();
        try {

            SQLiteDatabase sd3 = openOrCreateDatabase("coordinatorhelper", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            Cursor c = sd3.rawQuery("SELECT * from coordinator where cid=?", new String[]{cid});
            c.moveToFirst();
            wid = c.getString(5);


        }catch (SQLiteException e){

        }
        ParticipantsDbHelper pd=new ParticipantsDbHelper(ctx);
        Cursor c8=pd.getData(wid);
        if(c8!=null) {
            c8.moveToFirst();
            do {

                usn.add(c8.getString(0));
            } while (c8.moveToNext());
        }else{
            Toast.makeText(CoordiantorViewParticipants.this,"cursor",Toast.LENGTH_SHORT).show();
        }
        String[] usnr=new String[usn.size()];
        usnr=usn.toArray(usnr);



        StudentDbHelpher sdb=new StudentDbHelpher(ctx);

        for(int j=0;j<usnr.length;j++){
            Cursor c1=sdb.getData(usnr[j]);
            c1.moveToFirst();
            students.add(c1.getString(0));
            students.add(c1.getString(1));
        }

                ArrayAdapter<String> gridViewArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, students);
                gv.setAdapter(gridViewArrayAdapter);

    }
}
