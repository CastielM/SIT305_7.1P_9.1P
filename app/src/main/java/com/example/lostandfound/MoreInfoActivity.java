package com.example.lostandfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.lostandfound.databinding.ActivityMoreInfoBinding;

public class MoreInfoActivity extends AppCompatActivity {

    Intent intent;
    int id;

    ActivityMoreInfoBinding bindingInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bindingInfo = ActivityMoreInfoBinding.inflate(getLayoutInflater());
        setContentView(bindingInfo.getRoot());


        intent = getIntent();
        id = intent.getIntExtra("database_id", 0);

        bindingInfo.postType.setText(Integer.toString(id));


    }

    //Code to close activity when back button on menu bar is pressed, courtesy of stackoverflow
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}