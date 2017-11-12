package com.hp.technicalfest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class CoordinatorLogin extends AppCompatActivity {
    Button b1;
    EditText e1,e2;
    Context ctx=this;
    Pattern p1,p2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_login);
        b1 = (Button) findViewById(R.id.button_coordinator_login);
        e1 = (EditText) findViewById(R.id.coordiantor_workshop_id);
        e2 = (EditText) findViewById(R.id.coordinator_login_password);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=e1.getText().toString();
                String s2=e2.getText().toString();
                if(s1.equals(" ")){
                    e1.setError("Invalid Username");
                }else if(s2.equals(" ")){
                    e2.setError("Invalid Password");
                }else {
                    CoordinatorDbHelper ccd = new CoordinatorDbHelper(ctx);
                    if (ccd.checkUser(s1, s2)) {
                        Toast.makeText(CoordinatorLogin.this, "Login success", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(CoordinatorLogin.this, CoordinatorMainActivity.class);
                        i.putExtra("wid", s1);
                        startActivity(i);
                    } else {
                        Toast.makeText(CoordinatorLogin.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
