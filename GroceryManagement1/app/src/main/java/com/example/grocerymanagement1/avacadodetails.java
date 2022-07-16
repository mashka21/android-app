package com.example.grocerymanagement1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class avacadodetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avacadodetails);
        Spinner spinner= findViewById(R.id.spinner);

        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(avacadodetails.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.quantity));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);
    }
}