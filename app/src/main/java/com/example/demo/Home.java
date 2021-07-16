package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    Button im,cr,hr,finance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        im=findViewById(R.id.rtlim);
        cr=findViewById(R.id.rtlcr);
        hr=findViewById(R.id.rtlhr);
        finance=findViewById(R.id.finance);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Rtlim.class);
                startActivity(i);
            }
        });
    }
}