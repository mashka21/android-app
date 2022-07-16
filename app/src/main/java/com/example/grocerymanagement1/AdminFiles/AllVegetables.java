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

public class AllVegetables extends AppCompatActivity {

    RecyclerView recyclerView;
    AdminAdapter adminAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_vegetables);
        Toolbar toolbar = (Toolbar) findViewById(R.id.vegtoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("All Vegetables");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        recyclerView =findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<AdminModel> context = new FirebaseRecyclerOptions.Builder<AdminModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("vegetables"),AdminModel.class)
                .build();

        adminAdapter = new AdminAdapter(context,this,"vegetables");
        recyclerView.setAdapter(adminAdapter);


//        productModelList = new ArrayList<>();
//        recyclerView = findViewById(R.id.recyclerView);
//        //recyclerView.setHasFixedSize(true);
//        Toast.makeText(this, "welcome fruits", Toast.LENGTH_SHORT).show();

//        getFruits();

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

//    private void getFruits() {
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONArray products =new JSONArray(response);
//                            for(int i=0;i<products.length();i++){
//
//                                JSONObject object = products.getJSONObject(i);
//                                int id = object.getInt("id");
//                                String name = object.getString("productname");
//                                String price = object.getString("productprice");
//                                String image = object.getString("image");
//
//                                productModel product = new productModel(id,name, price,image);
//                                productModelList.add(product);
//                            }
//
//                            GridLayoutManager gridLayoutManager =new GridLayoutManager(AllFruits.this,1, GridLayoutManager.VERTICAL,false);
//                            adapter = new AdminAdapter(productModelList);
//                            recyclerView.setAdapter(adapter);
//                            recyclerView.setLayoutManager(gridLayoutManager);
//
//                            //adapter.notifyDataSetChanged();
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(AllFruits.this, error.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//        Volley.newRequestQueue(this).add(stringRequest);
//    }
}