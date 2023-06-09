package com.example.lostandfound;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.lostandfound.databinding.ActivityMainBinding;

import java.sql.Array;
import java.util.ArrayList;

// CastielM
//student ID: 220222729
//Unit SIT305
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //sets up button to go to Form Activity
        binding.newAdvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent gotoForm = new Intent(MainActivity.this, FormActivity.class);
                startActivity(gotoForm);
            }

        });

        //sets up button to go to Listings activity
        binding.viewListings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent gotoListings = new Intent(MainActivity.this, ListActivity.class);
                startActivity(gotoListings);
            }

        });

        binding.showMapButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });



    }
}