package com.example.grocerymanagement1.AdminFiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.grocerymanagement1.MainActivity;
import com.example.grocerymanagement1.R;
import com.google.firebase.auth.FirebaseAuth;

public class Admin extends AppCompatActivity {
    ImageView uploadFruit,uploadVegetable,OrganicHerbs,NewOnSeason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        uploadFruit = findViewById(R.id.uploadFruit);
        uploadVegetable = findViewById(R.id.uploadVegetable);
        OrganicHerbs = findViewById(R.id.uploadOrganics);
        NewOnSeason = findViewById(R.id.uploadNewSeosons);

        uploadFruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UploadProduct.class);
                intent.putExtra("mRef","Fruits");
                startActivity(intent);
            }
        });

        uploadVegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UploadProduct.class);
                intent.putExtra("mRef","vegetables");
                startActivity(intent);
            }
        });
        OrganicHerbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UploadProduct.class);
                intent.putExtra("mRef","organic");
                startActivity(intent);
            }
        });
        NewOnSeason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UploadProduct.class);
                intent.putExtra("mRef","newOnSeason");
                startActivity(intent);
            }
        });



    }


//    public void upload(View view) {
//        Intent intent = new Intent(this, UploadProduct.class);
//        startActivity(intent);
//    }

//    public void Allusers(View view) {
//        startActivity(new Intent(this,allUsers.class));
//    }


    public void allFruits(View view) {
        Intent intent = new Intent(this, AllFruits.class);
        intent.putExtra("mRef","Fruits");
        startActivity(intent);
    }
    public void allVegetables(View view) {
        startActivity(new Intent(this, AllVegetables.class));
    }
    public void allOrganicHerbs(View view) {
        startActivity(new Intent(this, AllOrganicHerbs.class));
    }
    public void allSeosons(View view) {
        startActivity(new Intent(this, NewOnSeason.class));
    }


    public void LogOut(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}