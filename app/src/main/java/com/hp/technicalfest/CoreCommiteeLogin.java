package com.hp.technicalfest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CoreCommiteeLogin extends AppCompatActivity {
    Button b1;
    EditText e1,e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core_commitee_login);
        b1 = (Button) findViewById(R.id.cc_login);
        e1 = (EditText) findViewById(R.id.cc_username);
        e2 = (EditText) findViewById(R.id.cc_password);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=e1.getText().toString();
                String password=e2.getText().toString();
                if(username.equals(" ")){
                    e1.setError("Invalid Username");
                }else if(password.equals(" ")){
                    e2.equals("Invalid Password");
                }
                if(username.equals("bmsce")&&password.equals("bmsce")){
                    Toast.makeText(CoreCommiteeLogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent i =new Intent(CoreCommiteeLogin.this,CoreCommitteeMainActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(CoreCommiteeLogin.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
