package com.example.lostandfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.lostandfound.databinding.ActivityFormBinding;



public class FormActivity extends AppCompatActivity {

    ActivityFormBinding bindingForm;
    DatabaseHelper databaseHelper;

    String inputPostType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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


                    db.insert("lostandfound", null, contentValues);
                    Toast.makeText(FormActivity.this, "Successfully posted", Toast.LENGTH_LONG).show();
                    finish();
                }

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