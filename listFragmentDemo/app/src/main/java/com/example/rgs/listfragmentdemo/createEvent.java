package com.example.rgs.listfragmentdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

public class createEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        final Button button = findViewById(R.id.back_button);
        final Intent intent = new Intent(this, MainActivity.class);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        final EditText event_name = findViewById(R.id.event_name);
        final EditText event_contents = findViewById(R.id.event_contents);
        final EditText event_tags = findViewById(R.id.event_tags);

        final Button create_button = findViewById(R.id.submit_button);
        create_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String name = event_name.getText().toString();
                final String contents = event_contents.getText().toString();
                final String tags = event_tags.getText().toString();


                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try  {
                            //Your code goes here
                            EventsManager.addPost(preferencesManager.getUsername(), preferencesManager.getPassword(), name, contents, tags);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                thread.start();

                startActivity(intent);
            }
        });
    }
}
