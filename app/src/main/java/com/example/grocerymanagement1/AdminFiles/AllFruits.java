package com.example.grocerymanagement1.AdminFiles;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.grocerymanagement1.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class AllFruits extends AppCompatActivity {

//    private String URL = "http://10.0.2.2/login/getProduct.php";
//
//
//    //private List<productModel> productModelList;
//    private List<productModel> productModelList;
//    RecyclerView recyclerView;
//    LinearLayoutManager layoutManager;
//    AdminAdapter adapter;

//    FirebaseDatabase mDatabase;
//    DatabaseReference mRef;
//    FirebaseStorage mStorage;
//    RecyclerView recyclerView;
//    AdminAdapter adminAdapter;
//    List<AdminModel> productList;

    RecyclerView recyclerView;
    AdminAdapter adminAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_fruits);

        Toolbar toolbar = (Toolbar) findViewById(R.id.fruittoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("All Fruits Product");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //String mRef=getIntent().getStringExtra("mRef");
        //Toast.makeText(this, "From "+mRef, Toast.LENGTH_SHORT).show();


//        mDatabase =FirebaseDatabase.getInstance();
//        mRef = mDatabase.getReference().child("Fruits");
//        mStorage = FirebaseStorage.getInstance();


//        recyclerView =findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView =findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<AdminModel> context = new FirebaseRecyclerOptions.Builder<AdminModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Fruits"),AdminModel.class)
                .build();

        adminAdapter = new AdminAdapter(context,this,"Fruits");
        recyclerView.setAdapter(adminAdapter);


        //productList = new ArrayList<AdminModel>();
        //adminAdapter = new AdminAdapter(AllFruits.this,productList);

//        mRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                AdminModel product = snapshot.getValue(AdminModel.class);
//                productList.add(product);
//                adminAdapter.notifyDataSetChanged();
//                recyclerView.setAdapter(adminAdapter);
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });



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