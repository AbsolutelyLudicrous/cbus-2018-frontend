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

public class secondListFragment extends ListFragment implements OnItemClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_list_fragment, container, false);
        return view;
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] abcArray = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N"};
        /*
        abcArray[0] = "A";
        abcArray[1] = "B";
        abcArray[2] = "C";
        */
        ArrayAdapter<String> abc_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, abcArray);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.Planets, android.R.layout.simple_list_item_1);
        setListAdapter(abc_adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        //Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), Interact_element.class);
        startActivity(intent);
    }
}
