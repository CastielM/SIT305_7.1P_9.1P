package com.example.lostandfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lostandfound.databinding.ActivityFormBinding;

public class FormActivity extends AppCompatActivity {

    ActivityFormBinding bindingForm;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        databaseHelper = new DatabaseHelper(this);

        bindingForm = ActivityFormBinding.inflate(getLayoutInflater());
        setContentView(bindingForm.getRoot());

        bindingForm.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(FormActivity.this, "Button Clicked", Toast.LENGTH_LONG).show();
            }

        });


    }
}