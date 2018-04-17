package com.example.rgs.listfragmentdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class upcoming_events extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_events);

        final Button reccomended_button = findViewById(R.id.button);
        final Intent intent = new Intent(this, MainActivity.class);
        reccomended_button.setOnClickListener(new View.OnClickListener() {
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
