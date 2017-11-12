package com.hp.technicalfest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddCoordinator extends AppCompatActivity {
    Spinner s;
    EditText et1,et2,et3,et4;
    Context ctx=this;
    Button b1;
    String e1;
    SQLiteDatabase sd;
    Pattern p1,p2,p3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coordinator);
        s= (Spinner) findViewById(R.id.spinner2);
        et1=(EditText)findViewById(R.id.cid);
        et2=(EditText)findViewById(R.id.cname);
        et3=(EditText)findViewById(R.id.ccontact);
        et4=(EditText)findViewById(R.id.cpassword);
        b1=(Button)findViewById(R.id.cregister);
        List<String> template=new ArrayList<>();
        try {
            sd = openOrCreateDatabase("sample", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            Cursor c = sd.rawQuery("SELECT * FROM workshop", null);
            c.moveToFirst();
            while (!c.isAfterLast()) {
                String s1 = c.getString(1);
                template.add(s1);
                c.moveToNext();
            }

            ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, template);
            dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s.setAdapter(dataAdapter1);
        }catch (SQLiteException e){

        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s2=s.getSelectedItem().toString();
                try{

                    SQLiteDatabase sd = openOrCreateDatabase("sample", SQLiteDatabase.CREATE_IF_NECESSARY, null);
                    String []params={s2};
                    Cursor c=sd.rawQuery("SELECT * FROM workshop where workshopname=?",params);
                    c.moveToFirst();
                     e1=c.getString(0);




                }catch (SQLiteException e){

                }
                p1= Pattern.compile("[0-9]{3,}");
                p2=Pattern.compile("[a-zA-z]{1,}");
                p3=Pattern.compile("[7-9][0-9]{9}");
                String e2= et1.getText().toString();
                String e3=et2.getText().toString();
                String e4=et3.getText().toString();
                String e5=et4.getText().toString();
                Matcher m1=p1.matcher(e2);
                Matcher m2= p2.matcher(e3);
                Matcher m3=p3.matcher(e4);
                Matcher m4=p2.matcher(e5);
                if(!m1.matches()){
                    et1.setError("Invalid CID");
                }else if(!m2.matches()){
                    et2.setError("Invalid Name");
                }else if(!m3.matches()){
                    et3.setError("Invalid Contact");
                }else if(!m4.matches()){
                    et4.setError("Invalid Password");
                }else {
                    CoordinatorDbHelper ccd = new CoordinatorDbHelper(ctx);
                    ccd.addCoordinator(ccd, e2, e1, e3, e4, e5, 1);

                    Toast.makeText(AddCoordinator.this, "Coordinator Added", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
