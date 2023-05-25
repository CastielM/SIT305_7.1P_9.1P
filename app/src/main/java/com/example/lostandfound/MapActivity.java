package com.example.lostandfound;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

    dataAdapter dataAdapter;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        newMap = googleMap;

        //TODO: add markers for all lost and found items in database
        //TODO: default camera to encompass all on map?
        LatLng exampleItem = new LatLng(-37.75773491458561, 145.00032517816857);
        newMap.addMarker(new MarkerOptions().position(exampleItem).title("Example Item"));
        newMap.moveCamera(CameraUpdateFactory.newLatLngZoom(exampleItem, 15));
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
