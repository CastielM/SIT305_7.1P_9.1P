package com.example.lostandfound;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lostandfound.databinding.ActivityFormBinding;

import java.util.ArrayList;


public class ListActivity extends AppCompatActivity implements DataListener{
    RecyclerView dataRecyclerView;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<DataModel> lostAndFoundData;

    dataAdapter dataAdapter;

    DatabaseHelper databaseHelper;

    String postType;
    String description;

    int database_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        dataRecyclerView = findViewById(R.id.recyclerview);

        databaseHelper = new DatabaseHelper(this);

        lostAndFoundData = new ArrayList<>();

        dataAdapter = new dataAdapter(this, lostAndFoundData, this);
        layoutManager = new LinearLayoutManager(this);

        dataRecyclerView.setAdapter(dataAdapter);
        dataRecyclerView.setLayoutManager(layoutManager);

        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        //adds each post_type and description to the table
        Cursor cursor = db.query("lostandfound", null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex("post_type");
            postType = cursor.getString(index);
            index = cursor.getColumnIndex("description");
            description = cursor.getString(index);
            index = cursor.getColumnIndex("id");
            database_id = cursor.getInt(index);
            lostAndFoundData.add(new DataModel(postType, description, database_id));
        }

        db.close();




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

    //gets length of database
    public int getLength(String tableName)
    {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.query(tableName, null, null, null, null, null, null);
        int count = cursor.getCount();

        return count;
    }

    @Override
    public void onItemClick(DataModel data) {
        Intent gotoData = new Intent(ListActivity.this, MoreInfoActivity.class);
        gotoData.putExtra("database_id", data.database_id);
        startActivity(gotoData);
    }
}


