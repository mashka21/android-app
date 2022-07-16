package com.example.grocerymanagement1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.grocerymanagement1.Database.OrderContract;
import com.example.grocerymanagement1.databinding.ActivityCartBinding;
import com.example.grocerymanagement1.databinding.ActivityFruitsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

//public class CartActivity extends DrawerBaseActivity implements LoaderManager.LoaderCallbacks<Cursor>{
public class CartActivity extends DrawerBaseActivity{
//    public CartAdapter mAdapter;
//    public  static final int LOADER =0;
    ActivityCartBinding activityCartBinding;

    int AllTotalAmount;
    TextView AllAmount;
    RecyclerView recyclerView;
    List<MyCartModel> cartModelList;
    MyCartAdapter cartAdapter;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCartBinding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(activityCartBinding.getRoot());
        allocateActivityTitle("My Cart");

        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mMessageReceicer,new IntentFilter("MyTotalAmount"));


        AllAmount =findViewById(R.id.sumTotal);
        recyclerView =findViewById(R.id.cart_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartModelList = new ArrayList<>();
        cartAdapter = new MyCartAdapter(getApplicationContext(),cartModelList);
        recyclerView.setAdapter(cartAdapter);

        fStore.collection("AddToCart").document(fAuth.getCurrentUser().getUid())
                .collection("UserOrder").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                MyCartModel myCartModel = doc.toObject(MyCartModel.class);
                                cartModelList.add(myCartModel);
                                cartAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });


//        Button cleardata = findViewById(R.id.deletebutton);
//        cleardata.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int deletaData = getContentResolver().delete(OrderContract.OrderEntry.CONTENT_URI,null,null);
//            }
//        });

//        LoaderManager.getInstance(this).initLoader(0, null, this);
//        ListView listView = findViewById(R.id.cart_List);
//        mAdapter = new CartAdapter(this,null);
//        listView.setAdapter(mAdapter);
    }

//
//    @Override
//    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
//        String[] projection = {OrderContract.OrderEntry._ID,
//                OrderContract.OrderEntry.COLUMN_NAME,
//                OrderContract.OrderEntry.COLUMN_PRICE,
//                OrderContract.OrderEntry.COLUMN_IMAGE,
//                OrderContract.OrderEntry.COLUMN_QUANTITY
//        };
//
//        return new CursorLoader(this, OrderContract.OrderEntry.CONTENT_URI,
//                projection,
//                null,
//                null,
//                null);
//
//    }
//
//    @Override
//    public void onLoadFinished( Loader<Cursor> loader, Cursor data) {
//        mAdapter.swapCursor(data);
//    }
//
//    @Override
//    public void onLoaderReset( Loader<Cursor> loader) {
//        mAdapter.swapCursor(null);
//    }
//




    public BroadcastReceiver mMessageReceicer = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int totolBill = intent.getIntExtra("totalAmount",0);
            AllAmount.setText(totolBill+"$");
        }
    };
}

