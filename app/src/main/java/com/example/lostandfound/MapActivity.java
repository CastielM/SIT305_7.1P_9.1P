package com.example.lostandfound;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap newMap;

    ArrayList<DataModel> lostAndFoundData;

    DatabaseHelper databaseHelper;

    double currentLatitude;
    double currentLongitude;

    Button cameraButton;

    FusedLocationProviderClient fusedLocationProviderClient;

    private static final int REQUEST_LOCATION_PERMISSION = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cameraButton = findViewById(R.id.zoomCamera);
        lostAndFoundData = new ArrayList<>();
        databaseHelper = new DatabaseHelper(this);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        cameraButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getCurrentLocation();

            }
        });


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
            LatLng item = new LatLng(latVal, longVal);
            newMap.addMarker(new MarkerOptions().position(item).title(location));
        }
            getCurrentLocation();



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

    private void getCurrentLocation() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        }
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                currentLatitude = location.getLatitude();
                                currentLongitude = location.getLongitude();
                                LatLng myLocation = new LatLng(currentLatitude, currentLongitude);
                                newMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 10));
                            }
                        }
                    });
        }
}
