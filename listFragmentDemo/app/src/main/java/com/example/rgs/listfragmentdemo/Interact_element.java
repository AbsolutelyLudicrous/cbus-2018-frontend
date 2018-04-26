package com.example.rgs.listfragmentdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.IOException;



public class Interact_element extends AppCompatActivity {

    public static JSONObject jo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interact_element);

        Intent intent = getIntent();

        final String PUUID = intent.getStringExtra("PUUID");
        final String title = intent.getStringExtra("title");
        final String owner = intent.getStringExtra("owner");
        final String contents = intent.getStringExtra("contents");
        final String comments = intent.getStringExtra("comments");
        final String RSVPers = intent.getStringExtra("RSVPers");
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

        TextView comments_textview = (TextView) findViewById(R.id.comments);
        comments_textview.setText(commentsString);

        final Button sendComment = findViewById(R.id.comment_button);
        final EditText user_comment_edittext = findViewById(R.id.input_comment);
        sendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user_comment = (String) user_comment_edittext.getText().toString();
                try {
                    Thread thread = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            try  {
                                //Your code goes here
                                EventsManager.comment(preferencesManager.getUsername(), preferencesManager.getPassword(), PUUID, user_comment);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    thread.start();

                    Intent intent = new Intent(Interact_element.this, Interact_element.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


                    String new_comments = "";

                    Thread secondThread = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            try  {
                                //Your code goes here
                                Interact_element.jo = EventsManager.getEventByPUUID(PUUID);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    thread.start();


                    new_comments = Interact_element.jo.getString("comments");


                    intent.putExtra("PUUID", PUUID);
                    intent.putExtra("title", title);
                    intent.putExtra("owner", owner);
                    intent.putExtra("contents", contents);
                    intent.putExtra("comments", new_comments);
                    intent.putExtra("RSVPers", RSVPers);
                    intent.putExtra("tags", tags);



                    startActivity(intent);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });







        final Button backbutton = findViewById(R.id.back_button);
        final Intent backIntent = new Intent(this, upcoming_events.class);
        backbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(backIntent);
            }
        });
    }
}
