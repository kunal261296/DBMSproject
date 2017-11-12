package com.hp.technicalfest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Student extends AppCompatActivity {
    Button b1,b2;
    String val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        b1 = (Button) findViewById(R.id.view_events);
        b2 = (Button) findViewById(R.id.register_events);
        Intent i=getIntent();
       val=i.getStringExtra("usn");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Student.this,temp.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Student.this,RegisterEventsStudent.class);
                i.putExtra("usn",val);
                startActivity(i);
            }
        });
    }
}
