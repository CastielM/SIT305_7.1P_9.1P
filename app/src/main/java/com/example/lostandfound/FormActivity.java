package com.example.lostandfound;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lostandfound.databinding.ActivityMainBinding;

public class FormActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        databaseHelper = new DatabaseHelper(this);


    }
}