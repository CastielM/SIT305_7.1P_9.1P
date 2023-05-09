package com.example.lostandfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lostandfound.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    Button newAdvertButton;
    Button viewListingsButton;
    TextView welcomeText;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newAdvertButton = findViewById(R.id.newAdvert);
        viewListingsButton = findViewById(R.id.viewListings);
        welcomeText = findViewById(R.id.welcomeText);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //sets up button to go to Form Activity
        newAdvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent gotoForm = new Intent(MainActivity.this, FormActivity.class);
                startActivity(gotoForm);
            }

        });

        //sets up button to go to Listings activity
        viewListingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent gotoListings = new Intent(MainActivity.this, ListActivity.class);
                startActivity(gotoListings);
            }

        });






    }
}