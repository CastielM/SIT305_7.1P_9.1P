package com.example.lostandfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.lostandfound.databinding.ActivityMoreInfoBinding;

public class MoreInfoActivity extends AppCompatActivity {

    Intent intent;
    int id;

    int index;

    String postValue;
    String nameValue;
    String dateValue;
    String locationValue;
    String descriptionValue;
    String phoneValue;



    ActivityMoreInfoBinding bindingInfo;

    DatabaseHelper databaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bindingInfo = ActivityMoreInfoBinding.inflate(getLayoutInflater());
        setContentView(bindingInfo.getRoot());


        intent = getIntent();
        id = intent.getIntExtra("database_id", 0);

        bindingInfo.infoPostType.setText(Integer.toString(id));

        databaseHelper = new DatabaseHelper(this);

        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM lostandfound WHERE id=" +id, null);
        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex("name");
            nameValue = cursor.getString(index);
            index = cursor.getColumnIndex("date");
            dateValue = cursor.getString(index);
            index = cursor.getColumnIndex("location");
            locationValue = cursor.getString(index);
            index = cursor.getColumnIndex("phone");
            phoneValue = cursor.getString(index);
            index = cursor.getColumnIndex("description");
            descriptionValue = cursor.getString(index);
            index = cursor.getColumnIndex("post_type");
            postValue = cursor.getString(index);

        }

        bindingInfo.infoName.setText("Name: " + nameValue);
        bindingInfo.infoPhone.setText("Contact: " + phoneValue);
        bindingInfo.infoDate.setText("on " + dateValue);
        bindingInfo.infoDescription.setText(descriptionValue);
        bindingInfo.infoLocation.setText("at " + locationValue);
        bindingInfo.infoPostType.setText(postValue);

        bindingInfo.removeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                db.delete("lostandfound", "id=?", new String[]{String.valueOf(id)});
                finish();
            }
        });


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