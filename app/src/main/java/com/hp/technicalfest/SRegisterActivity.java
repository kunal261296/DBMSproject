package com.hp.technicalfest;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SRegisterActivity extends AppCompatActivity {
    EditText et1,et2,et3,et4,et5,et6;
    Button b1;
    Context ctx=this;
    String usn,fname,lname,email,contact,password;
    Pattern p1,p2,p3,p4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sregister);
        final StudentDbHelpher db=new StudentDbHelpher(this);
        et1= (EditText) findViewById(R.id.usnregister);
        et2= (EditText) findViewById(R.id.firstnameregister);
        et3= (EditText) findViewById(R.id.lastnameregister);
        et4= (EditText) findViewById(R.id.emailregister);
        et5= (EditText) findViewById(R.id.contactregister);
        et6= (EditText) findViewById(R.id.passwordregister);
        b1= (Button) findViewById( R.id.rbutton);
         p1=Pattern.compile("1BM15[A-Z]{2}[0-9]{3}");
        p2=Pattern.compile("[a-zA-Z]{1,}");
        p3=Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");
        p4=Pattern.compile("[7-9][0-9]{9}");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usn = et1.getText().toString();
                fname = et2.getText().toString();
                lname = et3.getText().toString();
                email = et4.getText().toString();
                contact = et5.getText().toString();
                password = et6.getText().toString();
                Matcher m1=p1.matcher(usn);
                Matcher m2=p2.matcher(fname);
                Matcher m3=p2.matcher(lname);
                Matcher m4=p3.matcher(email);
                Matcher m5=p4.matcher(contact);
                Matcher m6=p2.matcher(password);
                if(!m1.matches()){
                    et1.setError("Invalid USN");
                }
                else if(!m2.matches()){
                    et2.setError("Invalid FirstName");

                }else if(!m3.matches()){
                    et3.setError("Invalid LastName");
                }else if(!m4.matches()){
                    et4.setError("Invalid Email");
                }else if(!m5.matches()){
                    et5.setError("Invalid PhoneNo");
                }
                else if(!m6.matches()){
                    et6.setError("Invalid Password");
                }
                else {
                    StudentDbHelpher sdb = new StudentDbHelpher(ctx);
                    if (sdb.checkUser(usn)) {
                        //Toast.makeText(SRegisterActivity.this,"User exists",Toast.LENGTH_SHORT).show();
                        Snackbar.make(v, "user exists", Snackbar.LENGTH_LONG).show();
                    } else {
                        sdb.addStudent(sdb, usn, fname, lname, email, contact, password);
                        Toast.makeText(SRegisterActivity.this, "Regsiteration successful", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(SRegisterActivity.this, SLoginActivity.class);
                        startActivity(i);
                    }
                }
            }
        });
    }
}
