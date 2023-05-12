package com.example.lostandfound;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lostandfound.databinding.ActivityFormBinding;



public class ListActivity extends AppCompatActivity {
    ActivityFormBinding bindingList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        bindingList = ActivityFormBinding.inflate(getLayoutInflater());
        setContentView(bindingList.getRoot());



    }
}