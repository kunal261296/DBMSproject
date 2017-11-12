package com.hp.technicalfest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SMainActivity extends AppCompatActivity {
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smain);
        b1= (Button) findViewById(R.id.registerbutton);
        b2= (Button) findViewById(R.id.loginbutton);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(SMainActivity.this,SRegisterActivity.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(SMainActivity.this,SLoginActivity.class);
                startActivity(j);
            }
        });
    }
}
