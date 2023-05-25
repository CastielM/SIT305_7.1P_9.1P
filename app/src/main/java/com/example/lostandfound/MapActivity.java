package com.example.lostandfound;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap newMap;

    ArrayList<DataModel> lostAndFoundData;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lostAndFoundData = new ArrayList<>();
        databaseHelper = new DatabaseHelper(this);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        newMap = googleMap;
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.query("lostandfound", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex("latitude");
            double latVal = cursor.getDouble(index);
            index = cursor.getColumnIndex("longitude");
            double longVal = cursor.getDouble(index);
            index = cursor.getColumnIndex("description");
            String location = cursor.getString(index);
            LatLng exampleItem = new LatLng(latVal, longVal);
            newMap.addMarker(new MarkerOptions().position(exampleItem).title(location));
            newMap.moveCamera(CameraUpdateFactory.newLatLngZoom(exampleItem, 15));

        }



    }

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
