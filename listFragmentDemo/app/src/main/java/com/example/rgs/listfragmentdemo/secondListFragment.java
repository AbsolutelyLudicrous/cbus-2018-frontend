package com.example.rgs.listfragmentdemo;

import android.annotation.SuppressLint;
import android.app.ListFragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import android.content.Intent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class secondListFragment extends ListFragment implements OnItemClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_list_fragment, container, false);
        return view;
    }


    public static JSONArray list_of_events = new JSONArray();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] abcArray = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N"};
        /*
        abcArray[0] = "A";
        abcArray[1] = "B";
        abcArray[2] = "C";
        */
        //ArrayAdapter<String> abc_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, abcArray);

        //ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                //R.array.Planets, android.R.layout.simple_list_item_1);




        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    //Your code goes here
                    String[] list_PUUID = preferencesManager.get_events();
                    for (int i = 0; i < list_PUUID.length; i++) {
                        secondListFragment.list_of_events.put(EventsManager.getEventByPUUID(list_PUUID[i]));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();




        //so that onItemClick() can access it

        ArrayList<String> eventsList = new ArrayList<>();
        String response;
        for (int i = 0; i < secondListFragment.list_of_events.length(); i++) {
            try {
                eventsList.add(secondListFragment.list_of_events.getJSONObject(i).getString("title"));
            } catch (JSONException e) {
                System.out.println(e);
            }
        }

        ArrayAdapter<String> events_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, eventsList);

        setListAdapter(events_adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        //Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getActivity(), Interact_element.class);
        String PUUID = "";
        String title = "";
        String owner = "";
        String contents = "";
        String comments = ""; //will JSONArray and JSONObject this later
        String RSVPers = "";
        String tags = "";
        try {
            PUUID = secondListFragment.list_of_events.getJSONObject(position).getString("PUUID");
            title = secondListFragment.list_of_events.getJSONObject(position).getString("title");
            owner = secondListFragment.list_of_events.getJSONObject(position).getString("owner");
            contents = secondListFragment.list_of_events.getJSONObject(position).getString("contents");
            comments = secondListFragment.list_of_events.getJSONObject(position).getString("comments");
            RSVPers = secondListFragment.list_of_events.getJSONObject(position).getString("RSVPers");
            tags = secondListFragment.list_of_events.getJSONObject(position).getString("tags");

        } catch (JSONException e) {
            System.out.println(e);
        }

        intent.putExtra("PUUID", PUUID);
        intent.putExtra("title", title);
        intent.putExtra("owner", owner);
        intent.putExtra("contents", contents);
        intent.putExtra("comments", comments);
        intent.putExtra("RSVPers", RSVPers);
        intent.putExtra("tags", tags);
        startActivity(intent);
    }
}
