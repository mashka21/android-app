package com.example.grocerymanagement1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView fruit1;
    ImageView vegetables;
    private ImageSlider imageSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fruit1 = findViewById(R.id.fruit1);
        vegetables = findViewById(R.id.vegetables);



        imageSlider = findViewById(R.id.imgSlider);

        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.basketfruit,null));
        slideModels.add(new SlideModel(R.drawable.vegetablebasket,null));
        slideModels.add(new SlideModel(R.drawable.cabbage,null));
        slideModels.add(new SlideModel(R.drawable.aple,null));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

    }


    public void OpenFruits(View view) {
        Intent intent = new Intent(this, Fruits.class);
        startActivity(intent);
    }
    public void OpenVegetable(View view) {
        Intent intent = new Intent(this, vegetables.class);
        startActivity(intent);

    }

}