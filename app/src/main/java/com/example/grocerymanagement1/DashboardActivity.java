package com.example.grocerymanagement1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.grocerymanagement1.AdminFiles.NewOnSeason;
import com.example.grocerymanagement1.databinding.ActivityDashboardBinding;

import java.util.ArrayList;

public class DashboardActivity extends DrawerBaseActivity {

    ActivityDashboardBinding activityDashboardBinding;
    ImageView fruit1;
    ImageView vegetables;
    private ImageSlider imageSlider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDashboardBinding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(activityDashboardBinding.getRoot());
        allocateActivityTitle("Dashboard");


        fruit1 = findViewById(R.id.fruit1);
        vegetables = findViewById(R.id.vegetables);

        imageSlider = findViewById(R.id.imgSlider);

        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.vegetablebasket,null));
        slideModels.add(new SlideModel(R.drawable.vegetable,null));
        slideModels.add(new SlideModel(R.drawable.basketfruit,null));
        slideModels.add(new SlideModel(R.drawable.bnn,null));
        slideModels.add(new SlideModel(R.drawable.frtbsket,null));
        slideModels.add(new SlideModel(R.drawable.veg,null));


        imageSlider.setImageList(slideModels, ScaleTypes.FIT);


    }

    public void OpenFruits(View view) {
        Intent intent = new Intent(this,Fruits.class);
        startActivity(intent);
    }

    public  void OpenVegetable(View view) {
        Intent intent = new Intent(this, vegetables.class);
        startActivity(intent);
    }
    public  void Openherbs(View view) {
        Intent intent = new Intent(this, organicHerbs.class);
        startActivity(intent);
    }
    public  void openNewSeason(View view) {
        Intent intent = new Intent(this, NewSeason.class);
        startActivity(intent);
    }


}