package com.example.lostandfound;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.lostandfound.databinding.ActivityFormBinding;

import java.util.ArrayList;


public class ListActivity extends AppCompatActivity {
    RecyclerView dataRecyclerView;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<DataModel> lostAndFoundData;

    dataAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        dataRecyclerView = findViewById(R.id.recyclerview);

        lostAndFoundData = new ArrayList<>();


        for (int i = 0; i < 10; i++ )
        {
            //TODO this needs to pull from database
            String posttype = "Name " + Integer.toString(i);
            String description = "This is description number " + Integer.toString(i);
            lostAndFoundData.add(new DataModel(posttype, description));
        }


        dataAdapter = new dataAdapter(this, lostAndFoundData);
        layoutManager = new LinearLayoutManager(this);

        dataRecyclerView.setAdapter(dataAdapter);
        dataRecyclerView.setLayoutManager(layoutManager);



    }
}