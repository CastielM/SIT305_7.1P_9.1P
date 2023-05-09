package com.example.lostandfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lostandfound.databinding.ActivityFormBinding;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


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
                SQLiteDatabase db = databaseHelper.getWritableDatabase();

                ContentValues contentValues = new ContentValues();
                String inputName = bindingForm.inputName.getText().toString();
                String inputPhone = bindingForm.inputPhone.getText().toString();
                String inputDescription = bindingForm.inputDescription.getText().toString();
                String inputLocation =  bindingForm.inputLocation.getText().toString();
                String inputDate =  bindingForm.inputDate.getText().toString();



                contentValues.put("name", inputName);
                contentValues.put("phone", inputPhone);
                contentValues.put("description", inputDescription);
                contentValues.put("location", inputLocation);
                contentValues.put("date", inputDate);
                //contentValues.put("post_type", inputPostType);


                db.insert("lostandfound", null, contentValues);


            }

        });


    }
}