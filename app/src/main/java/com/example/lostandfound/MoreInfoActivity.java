package com.example.lostandfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.lostandfound.databinding.ActivityMoreInfoBinding;

public class MoreInfoActivity extends AppCompatActivity {

    Intent intent;
    int id;

    ActivityMoreInfoBinding bindingInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);

        bindingInfo = ActivityMoreInfoBinding.inflate(getLayoutInflater());
        setContentView(bindingInfo.getRoot());


        intent = getIntent();
        id = intent.getIntExtra("database_id", 0);

        bindingInfo.postType.setText(Integer.toString(id));


    }
}