package com.hp.technicalfest;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CoordinatorMainActivity extends AppCompatActivity {
    Button b1,b2;
    String wid;
    Cursor c;

    Context ctx=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_main);
        b1 = (Button) findViewById(R.id.view_participants);
        b2 = (Button) findViewById(R.id.close_event);

        Intent i=getIntent();
        wid=i.getStringExtra("wid");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(CoordinatorMainActivity.this,CoordiantorViewParticipants.class);
                i.putExtra("w",wid);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                try {
                    SQLiteDatabase sd = openOrCreateDatabase("coordinatorhelper", SQLiteDatabase.CREATE_IF_NECESSARY, null);
                    sd.execSQL("UPDATE coordinator SET workshopstatus=0 where "+CoordinatorDbHelper.COLUMN_COORDINATOR_WORKSHOP_ID+"=wid");
                    Snackbar.make(v,"Event Closed",Snackbar.LENGTH_LONG).show();
                }catch (SQLiteException e){

                }

            }
        });
    }
}
