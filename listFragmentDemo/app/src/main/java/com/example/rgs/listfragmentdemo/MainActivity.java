package com.example.rgs.listfragmentdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

import org.json.JSONArray;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button upcoming_button = findViewById(R.id.button);
        final Intent intent = new Intent(this, upcoming_events.class);
        upcoming_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        final Button createEvent_button = findViewById(R.id.button2);
        final Intent newIntent = new Intent(this, createEvent.class);
        createEvent_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(newIntent);
            }
        });
    }
}
