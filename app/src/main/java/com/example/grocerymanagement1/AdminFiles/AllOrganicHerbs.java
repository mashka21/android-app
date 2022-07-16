package com.example.grocerymanagement1.AdminFiles;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grocerymanagement1.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class AllOrganicHerbs extends AppCompatActivity {
    RecyclerView recyclerView;
    AdminAdapter adminAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organic_herbs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.herbstoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Organic Herbs");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        recyclerView =findViewById(R.id.herbs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<AdminModel> context = new FirebaseRecyclerOptions.Builder<AdminModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("organic"),AdminModel.class)
                .build();

        adminAdapter = new AdminAdapter(context,this,"organic");
        recyclerView.setAdapter(adminAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        adminAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adminAdapter.stopListening();
    }


}