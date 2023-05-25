package com.example.lostandfound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.lostandfound.databinding.ActivityFormBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class FormActivity extends AppCompatActivity {

    ActivityFormBinding bindingForm;
    DatabaseHelper databaseHelper;

    String inputPostType;

    double latVal;
    double longVal;

    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_LOCATION_PERMISSION = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseHelper = new DatabaseHelper(this);

        bindingForm = ActivityFormBinding.inflate(getLayoutInflater());
        setContentView(bindingForm.getRoot());
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        bindingForm.currentLocation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getCurrentLocation();


            }
        });

        bindingForm.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                SQLiteDatabase db = databaseHelper.getWritableDatabase();

                ContentValues contentValues = new ContentValues();
                String inputName = bindingForm.inputName.getText().toString();
                String inputPhone = bindingForm.inputPhone.getText().toString();
                String inputDescription = bindingForm.inputDescription.getText().toString();
                String inputLocation =  bindingForm.inputLocation.getText().toString();
                String inputDate =  bindingForm.inputDate.getText().toString();

                //Checks which radio button is checked
                int checkRadioButton = bindingForm.radioGroup.getCheckedRadioButtonId();
                if (checkRadioButton == R.id.foundButton)
                {
                    inputPostType = "Found";
                }
                else if (checkRadioButton == R.id.lostButton)
                {
                    inputPostType = "Lost";
                }

                if (inputName.length() == 0 || inputPhone.length() == 0 || inputDescription.length() == 0 || inputLocation.length() == 0 || inputDate.length() == 0 || checkRadioButton < 0)
                {
                    Toast.makeText(FormActivity.this, "Please fill in all fields", Toast.LENGTH_LONG).show();
                }
                else {
                    contentValues.put("name", inputName);
                    contentValues.put("phone", inputPhone);
                    contentValues.put("description", inputDescription);
                    contentValues.put("location", inputLocation);
                    contentValues.put("date", inputDate);
                    contentValues.put("post_type", inputPostType);
                    contentValues.put("latitude", latVal);
                    contentValues.put("longitude", longVal);


                    db.insert("lostandfound", null, contentValues);
                    Toast.makeText(FormActivity.this, "Successfully posted", Toast.LENGTH_LONG).show();
                    finish();
                }

            }

        });





    }

    private void getCurrentLocation()
    {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        }
        else {
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null)
                            {
                                Geocoder geocoder = new Geocoder(FormActivity.this, Locale.getDefault());
                                List<Address> addresses;
                                try {
                                    latVal = location.getLatitude();
                                    longVal = location.getLongitude();
                                    addresses = geocoder.getFromLocation(latVal,longVal, 1);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                String addressName = addresses.get(0).getAddressLine(0);

                                bindingForm.inputLocation.setText(addressName);
                                //TODO will have to add lat and long to database so that markers can be used, use REAL datatype.

                            }
                        }
                    });
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                getCurrentLocation();
            }
        }
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