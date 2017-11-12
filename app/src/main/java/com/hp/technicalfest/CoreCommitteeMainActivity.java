package com.hp.technicalfest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CoreCommitteeMainActivity extends AppCompatActivity {
    Button b1,b2,b3,b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core_committee_main);
        b1 = (Button) findViewById(R.id.add_event);

        b3=(Button)findViewById(R.id.addcoordinator);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(CoreCommitteeMainActivity.this,AddEvent.class);
                startActivity(i);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(CoreCommitteeMainActivity.this,AddCoordinator.class);
                startActivity(i);
            }
        });

;
    }
}
