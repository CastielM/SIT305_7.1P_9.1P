package com.example.lostandfound;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.lostandfound.databinding.ActivityFormBinding;

import java.util.ArrayList;


public class ListActivity extends AppCompatActivity {
    RecyclerView dataRecyclerView;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<DataModel> lostAndFoundData;

    dataAdapter dataAdapter;

    DatabaseHelper databaseHelper;

    String postType;
    String description;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        dataRecyclerView = findViewById(R.id.recyclerview);

        databaseHelper = new DatabaseHelper(this);

        lostAndFoundData = new ArrayList<>();

        dataAdapter = new dataAdapter(this, lostAndFoundData);
        layoutManager = new LinearLayoutManager(this);

        dataRecyclerView.setAdapter(dataAdapter);
        dataRecyclerView.setLayoutManager(layoutManager);

        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        //adds each post_type and description to the table
        Cursor cursor = db.query("lostandfound", null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex("post_type");
            postType = cursor.getString(index);
            index =cursor.getColumnIndex("description");
            description = cursor.getString(index);

            lostAndFoundData.add(new DataModel(postType, description));
        }




    }

    public int getLength(String tableName)
    {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.query(tableName, null, null, null, null, null, null);
        int count = cursor.getCount();

        return count;
    }

}
