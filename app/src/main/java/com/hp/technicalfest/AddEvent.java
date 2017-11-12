package com.hp.technicalfest;

import android.content.Context;
import android.content.Intent;
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

public class AddEvent extends AppCompatActivity {
    EditText et1,et2,et4,et5,et6;
    Spinner s1;
    Button b1;
    Context ctx=this;
    Pattern p1,p2,p3,p4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        et1= (EditText) findViewById(R.id.workshop_id);
        et2= (EditText) findViewById(R.id.workshop_name);
        s1= (Spinner) findViewById(R.id.workshop_type);
        et4= (EditText) findViewById(R.id.workshop_venue);
        et5= (EditText) findViewById(R.id.workshop_time);
        et6= (EditText) findViewById(R.id.workshop_price);
        b1= (Button) findViewById(R.id.button_add_events);
        List<String> template=new ArrayList<>();
        template.add("Workshop");
        template.add("Coding");
        template.add("Hackathon");
        template.add("Seminar");
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, template);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(dataAdapter1);

        p1=Pattern.compile("[0-9]{3,}");
        p2=Pattern.compile("[a-zA-z]{1,}");
        p3=Pattern.compile("[0-9.-a-zA-Z]{1,5}");
        p4=Pattern.compile("[0-9]{1,}");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoreCommiteeDbHelper ccdb = new CoreCommiteeDbHelper(ctx);
                String wid = et1.getText().toString();
                String wname = et2.getText().toString();
                String type = s1.getSelectedItem().toString();
                String vnue = et4.getText().toString();
                String time = et5.getText().toString();
                String p = et6.getText().toString();
                int price = Integer.parseInt(et6.getText().toString());
                Matcher m1 = p1.matcher(wid);
                Matcher m2 = p2.matcher(wname);

                Matcher m4 = p2.matcher(vnue);
                Matcher m5 = p3.matcher(time);
                Matcher m6 = p4.matcher(p);
                if (!m1.matches()) {
                    et1.setError("Invalid WID");
                } else if (!m2.matches()) {
                    et2.setError("Invalid Name");
                }
                 else if (!m4.matches()) {
                    et4.setError("Invalid Venue");
                } else if (!m5.matches()) {
                    et5.setError("Invalid Time");
                } else if (!m6.matches()) {
                    et6.setError("Invalid Price");
                } else {


                    ccdb.addWorkshop(ccdb, wid, wname, type, vnue, time, price);
                    Toast.makeText(AddEvent.this, "workshop added succeddfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AddEvent.this, AddCoordinator.class);
                    startActivity(i);
                    finish();
                }
            }
        });

    }
}
