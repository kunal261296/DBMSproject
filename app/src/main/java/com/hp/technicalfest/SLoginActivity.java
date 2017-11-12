package com.hp.technicalfest;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SLoginActivity extends AppCompatActivity {
    EditText et1,et2;
    Button b1;
   Context ctx=this;
Pattern p1,p2;
    String u,p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slogin);

        et1=(EditText)findViewById(R.id.usnlogin);
        et2=(EditText)findViewById(R.id.passwordlogin);
        b1=(Button)findViewById(R.id.lbutton);
        p1= Pattern.compile("1BM15[A-Z]{2}[0-9]{3}");
        p2=Pattern.compile("[a-zA-Z]{1,}");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u=et1.getText().toString();
                p=et2.getText().toString();
               Matcher m1=p1.matcher(u);
                Matcher m2=p2.matcher(p);
                if(!m1.matches()){
                    et1.setError("Invalid USN");
                }else if(!m2.matches()){
                    et2.setError("Invalid Password");
                }else {
                    StudentDbHelpher sdb = new StudentDbHelpher(ctx);
                    if (sdb.checkUser(u, p)) {
                        Toast.makeText(SLoginActivity.this, "login success", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(SLoginActivity.this, Student.class);
                        i.putExtra("usn", u);
                        startActivity(i);
                    } else {
                        Toast.makeText(SLoginActivity.this, "login failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
