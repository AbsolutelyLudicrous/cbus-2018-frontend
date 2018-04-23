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

public class MyListFragment extends ListFragment implements OnItemClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        return view;
    }


    public static JSONArray list_of_events;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] abcArray = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N"};
        /*
        abcArray[0] = "A";
        abcArray[1] = "B";
        abcArray[2] = "C";
        */

        JSONArray ja = new JSONArray();

        try {
            ja = EventsManager.getEventsByTags(preferencesManager.get_tags());
        } catch (java.io.IOException e) {
            System.out.println(e);
        }

        MyListFragment.list_of_events = ja;
        //so that onItemClick() can access it

        ArrayList<String> eventsList = new ArrayList<>();
        String response;
        for (int i = 0; i < ja.length(); i++) {
            try {
                eventsList.add(ja.getJSONObject(i).getString("title"));
            } catch (JSONException e) {
                System.out.println(e);
            }
        }





        //ArrayAdapter<String> abc_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, abcArray);
        ArrayAdapter<String> events_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, eventsList);
        //ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                //R.array.Planets, android.R.layout.simple_list_item_1);
        setListAdapter(events_adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        //Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), DisplayElementActivity.class);
        String PUUID = "";
        String title = "";
        String owner = "";
        String contents = "";
        String comments = ""; //will JSONArray and JSONObject this later
        String RSVPers = "";
        String tags = "";
        try {
            PUUID = MyListFragment.list_of_events.getJSONObject(position).getString("PUUID");
            title = MyListFragment.list_of_events.getJSONObject(position).getString("title");
            owner = MyListFragment.list_of_events.getJSONObject(position).getString("owner");
            contents = MyListFragment.list_of_events.getJSONObject(position).getString("contents");
            comments = MyListFragment.list_of_events.getJSONObject(position).getString("comments");
            RSVPers = MyListFragment.list_of_events.getJSONObject(position).getString("RSVPers");
            tags = MyListFragment.list_of_events.getJSONObject(position).getString("tags");

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
