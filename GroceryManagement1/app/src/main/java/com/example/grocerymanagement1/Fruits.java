package com.example.grocerymanagement1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Fruits extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits);


    }

    public void OpenAvacado(View view) {
        Intent intent = new Intent(this, avacadodetails.class);
        startActivity(intent);
    }
    public void OpenBanana(View view) {
        Intent intent = new Intent(this, bananadetails.class);
        startActivity(intent);
    }
    public void OpenPineapple(View view) {
        Intent intent = new Intent(this, pineappledetails.class);
        startActivity(intent);
    }
    public void Openapple(View view) {
        Intent intent = new Intent(this, appledetails.class);
        startActivity(intent);
    }
    public void Openmango(View view) {
        Intent intent = new Intent(this, mangodetails.class);
        startActivity(intent);

    }
    public void Opencoconut(View view) {
        Intent intent = new Intent(this, coconutdetails.class);
        startActivity(intent);
    }
}