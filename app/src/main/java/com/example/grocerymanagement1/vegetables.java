package com.example.grocerymanagement1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.grocerymanagement1.databinding.ActivityVegetablesBinding;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class vegetables extends DrawerBaseActivity {

//    ArrayList<ProductModelClass> myproducts;
//    Adapter adapter;
//    androidx.recyclerview.widget.RecyclerView RecyclerView ;

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    List<productModel> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetables);

        Toolbar toolbar = (Toolbar) findViewById(R.id.fruittoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDatabase =FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("vegetables");
        mStorage = FirebaseStorage.getInstance();
        recyclerView =findViewById(R.id.Vegetables);
        GridLayoutManager gridLayoutManager =new GridLayoutManager(vegetables.this,2, GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);

        productList = new ArrayList<productModel>();
        productAdapter = new ProductAdapter(vegetables.this,productList);

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                productModel product = snapshot.getValue(productModel.class);
                productList.add(product);
                productAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(productAdapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        myproducts=new ArrayList<>();
//        myproducts.add(new ProductModelClass(R.drawable.potato,"potato","200","Rich in nutrients. Bananas contain a fair amount of fiber and several antioxidants. One …\n" +
//                "May improve blood sugar levels. Bananas are rich in soluble fiber"));
//        myproducts.add(new ProductModelClass(R.drawable.carrot,"carrot","3000","“An apple a day keeps the doctor away.”"));
//        myproducts.add(new ProductModelClass(R.drawable.cabbage,"cabbage","500"," Mangoes improve skin and hair health The vitamin A in mangos is also key for the development and maintenance of multiple types of epithelial tissues, including skin, hair"));
//        myproducts.add(new ProductModelClass(R.drawable.tomato,"tomato","100","The Skinny on This Healthy Fat Fruit Avocados offer an abundance of fiber, potassium (more than a banana!), and vitamins B6 and C"));
//        myproducts.add(new ProductModelClass(R.drawable.onion,"onion","400","Coconut is the fruit of the coconut palm (Cocos nucifera). It’s used for its water, milk, oil, and tasty meat."));
//        myproducts.add(new ProductModelClass(R.drawable.capsi,"capsicum","2000","The health benefits of lemon are attributed to its vitamin C content that helps improve the kin quality, encourages weight loss, improves digestion, and acts as a breath freshener. "));
//        myproducts.add(new ProductModelClass(R.drawable.salad,"salad","1500","Pineapple is high in vitamin C, which helps your immune system -- the body's defense against germs -- keep you healthy. It also has: Vitamins A, B6, E, and K"));
//        myproducts.add(new ProductModelClass(R.drawable.spinach,"spinach","2500","One of the important factor people consumes papaya is that it is good for digestion and give you relief from a toothache etc"));
//        myproducts.add(new ProductModelClass(R.drawable.chilli,"chilli","2500","Boosts the body's immune system to protect against viruses and germs. Improves iron absorption and fights anemia. Slows age-related macular degeneration (AMD)"));
//        myproducts.add(new ProductModelClass(R.drawable.ginger,"ginger","100","Watermelon, Citrullus lanatus, is a vining annual plant in the family Cucurbitaceae grown for its fleshy fruit"));
//



//        RecyclerView=findViewById(R.id.Vegetables);
//
//
//        GridLayoutManager gridLayoutManager =new GridLayoutManager(vegetables.this,2, GridLayoutManager.VERTICAL,false);
//        adapter=new Adapter(myproducts,vegetables.this);
//        RecyclerView.setAdapter(adapter);
//        RecyclerView.setLayoutManager(gridLayoutManager);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.search_menu,menu);
//
//        MenuItem menuItem = menu.findItem(R.id.search_view);
//        SearchView searchView = (SearchView) menuItem.getActionView();
//        searchView.setQueryHint("Search Here...");
//
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                filterlist(newText);
//                return true;
//            }
//        });
//
//
//
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    private void filterlist(String text) {
//        ArrayList<ProductModelClass> filterlist = new ArrayList<>();
//        for (ProductModelClass item : myproducts){
//            if(item.getproductName().toLowerCase().contains(text.toLowerCase())){
//                filterlist.add(item);
//            }
//        }
//        if(filterlist.isEmpty()){
//            Toast.makeText(this, "Not founded", Toast.LENGTH_SHORT).show();
//        }else {
//            adapter.setFilteredList(filterlist);
//        }
//    }
}
