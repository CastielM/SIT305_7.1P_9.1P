package com.example.lostandfound;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class FormActivity extends AppCompatActivity {

    Button saveButton;
    TextView nameText;
    TextView phoneText;
    TextView descriptionText;
    TextView dateText;
    TextView locationText;
    TextView postTypeText;
    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText nameInput;
    EditText phoneInput;
    EditText descriptionInput;
    EditText DateInput;
    EditText LocationInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);






    }
}