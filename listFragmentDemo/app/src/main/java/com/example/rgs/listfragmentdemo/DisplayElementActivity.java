package com.example.rgs.listfragmentdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

public class DisplayElementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_element);

        Intent intent = getIntent();

        final String PUUID = intent.getStringExtra("PUUID");
        String title = intent.getStringExtra("title");
        String owner = intent.getStringExtra("owner");
        String contents = intent.getStringExtra("contents");
        String comments = intent.getStringExtra("comments");
        String RSVPers = intent.getStringExtra("RSVPers");
        final String tags = intent.getStringExtra("tags");


        //event received
        JSONArray RSVPArray = new JSONArray();
        JSONArray commentsArray = new JSONArray();
        try {
            RSVPArray = new JSONArray(RSVPers);
            commentsArray = new JSONArray(comments);
        } catch (JSONException e) {
            System.out.println(e);
        }

        String commentsString = "";
        String commenter = "";
        String comment_body = "";
        for (int i = 0; i < commentsArray.length(); i++) {
            try {
                commenter = commentsArray.getJSONObject(i).getString("commenter");
                comment_body = commentsArray.getJSONObject(i).getString("body");
                commentsString = commentsString + "User: " + commenter + "\n" + "Commented: " + comment_body + "\n\n";
            } catch (JSONException e) {
                System.out.println(e);
            }
        }

        TextView text_owner = findViewById(R.id.Owner);
        text_owner.setText("By: " + owner);

        TextView text_contents = findViewById(R.id.contents);
        text_contents.setText(contents + "\n\nThe number of RSVPers to this event is " + RSVPArray.length());

        TextView text_comments = findViewById(R.id.comments);
        text_comments.setText(commentsString);


        //RSVP
        final Button createEvent_button = findViewById(R.id.go);
        final Intent newIntent = new Intent(this, MainActivity.class);
        createEvent_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    preferencesManager.append_event(PUUID);
                    preferencesManager.append_tag(tags);
                } catch (IOException e) {
                    System.out.println(e);
                }
                startActivity(newIntent);
            }
        });


        //String message = "Woohoo this worked";

        //TextView textView = findViewById(R.id.textView);
        //textView.setText(message);
    }
}
