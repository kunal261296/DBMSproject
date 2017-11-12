package com.hp.technicalfest;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appdatasearch.RegisterSectionInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterEventsStudent extends AppCompatActivity {
    Button b1;
    Spinner s1;
    SQLiteDatabase sd;
    EditText et;
    String e2,e3,usn,contactstudent,cname,contactc,venue,time,wname,message;
    int status;
    Context ctx=this;
   Pattern p1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_events_student);
        s1=(Spinner)findViewById(R.id.spinner);
        b1=(Button)findViewById(R.id.regiterfinal);
        et=(EditText)findViewById(R.id.transaction_id);
        p1=Pattern.compile("[a-zA-Z0-9]{10}");
        Intent i=getIntent();
    usn=i.getStringExtra("usn");
        List<String> template=new ArrayList<>();
        try{
            sd=openOrCreateDatabase("sample", SQLiteDatabase.CREATE_IF_NECESSARY,null);
            Cursor c=sd.rawQuery("SELECT * FROM workshop",null);
            c.moveToFirst();
            while(!c.isAfterLast()){
                String s1=c.getString(1);
                template.add(s1);
                c.moveToNext();
            }

            ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, template);
            dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s1.setAdapter(dataAdapter1);
        }catch (SQLiteException e){

        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e1=s1.getSelectedItem().toString();
                try{

                    SQLiteDatabase sd1 = openOrCreateDatabase("sample", SQLiteDatabase.CREATE_IF_NECESSARY, null);
                    String []params={e1};
                    Cursor c1=sd1.rawQuery("SELECT * FROM workshop where workshopname=?",params);
                    c1.moveToFirst();
                    e2=c1.getString(0);

                }catch (SQLiteException e){

                }
                try{
                    SQLiteDatabase sd1 = openOrCreateDatabase("coordinatorhelper", SQLiteDatabase.CREATE_IF_NECESSARY, null);
                    String []params={e2};
                    Cursor c1=sd1.rawQuery("SELECT * FROM coordinator where wid=?",params);
                    c1.moveToFirst();
                    status=Integer.parseInt(c1.getString(4));
                    cname=c1.getString(1);
                    contactc=c1.getString(2);

                }catch (SQLiteException e){
                }
                 e3=et.getText().toString();
                Matcher m1=p1.matcher(e3);
                if(!m1.matches()){
                    et.setError("Invalid TransactionID");
                }else {
                    try{
                        SQLiteDatabase sd=openOrCreateDatabase("Main",SQLiteDatabase.CREATE_IF_NECESSARY,null);
                        String[] params={usn};
                        Cursor c=sd.rawQuery("select * from Student where usn=?",params);
                        c.moveToFirst();
                        contactstudent=c.getString(4);
                    }catch(SQLiteException e){

                    }
                    try{
                        SQLiteDatabase sd=openOrCreateDatabase("sample",SQLiteDatabase.CREATE_IF_NECESSARY,null);
                        String[] params={e2};
                        Cursor c=sd.rawQuery("select * from workshop where wid=?",params);
                        c.moveToFirst();
                        wname=c.getString(1);
                        venue=c.getString(3);
                        time=c.getString(4);
                    }catch(SQLiteException e){

                    }
                    if (status == 1) {
                        ParticipantsDbHelper pd = new ParticipantsDbHelper(ctx);
                        if (pd.addParticpants(pd, usn, e2, e3, 0) != -1) {

                            Toast.makeText(RegisterEventsStudent.this, "Registeration successfull", Toast.LENGTH_SHORT).show();
                            message="Your Registeration for event "+wname.toUpperCase()+" is confirmed!!!" +
                                    " Venue : "+venue+" Time : "+time+" Details of coordinator  Name : "+cname+" Contact no : "+contactc;
                            SmsManager sms=SmsManager.getDefault();
                            sms.sendTextMessage(contactstudent,null,message,null,null);
                        } else {
                            Toast.makeText(RegisterEventsStudent.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Snackbar.make(v, "Event Closed", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
