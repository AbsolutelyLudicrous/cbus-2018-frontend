package com.example.rgs.listfragmentdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

public class DisplayElementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_element);

        Intent intent = getIntent();
        //String message = "Woohoo this worked";

        //TextView textView = findViewById(R.id.textView);
        //textView.setText(message);
    }
}
