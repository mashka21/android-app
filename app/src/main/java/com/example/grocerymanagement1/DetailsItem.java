package com.example.grocerymanagement1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.grocerymanagement1.Database.OrderContract;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

//public class DetailsItem extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
public class DetailsItem extends AppCompatActivity{
    ImageView details_image;
    TextView details_name,details_price,descriptionInfo;
//    ImageView detailsIamge;
//    TextView detailsName;
//    TextView detailsPrice;
//    TextView detailsDescription;
    String realPrice = "";
    String img;
    TextView quantity;
    TextView totalPrice;
    Button addToCart;
    String imageUri = null;
    int count =0;
    int total = 0;
    public Uri mCurrentCartUri;
    boolean hasAllRequiredValues = false;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_item);

        details_image = findViewById(R.id.details_image);
        details_name = findViewById(R.id.details_name);
        details_price = findViewById(R.id.details_price);
        descriptionInfo =findViewById(R.id.descriptionInfo);




        img = getIntent().getStringExtra("image");
        //details_image.setImageResource(Integer.parseInt(img));

        imageUri = img;
        Picasso.get().load(imageUri).into(details_image);
        details_name.setText(getIntent().getStringExtra("name"));
        details_price.setText(getIntent().getStringExtra("price"));
        descriptionInfo.setText(getIntent().getStringExtra("desc"));



//        detailsIamge = findViewById(R.id.details_image);
//        detailsName = findViewById(R.id.details_name);
//        detailsPrice = findViewById(R.id.details_price);
//        detailsDescription = findViewById(R.id.descriptionInfo);

        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        addToCart = findViewById(R.id.addToCart);

        quantity = (TextView) findViewById(R.id.quantity);
        totalPrice = (TextView) findViewById(R.id.totalPrice);


        //get data from recyvlerview
//        detailsIamge.setImageResource(getIntent().getIntExtra("image",R.drawable.avacado));
//        detailsName.setText(getIntent().getStringExtra("name"));
//        detailsPrice.setText(getIntent().getStringExtra("price"));
//        detailsDescription.setText(getIntent().getStringExtra("Description"));
//
//
        realPrice = details_price.getText().toString();




        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(DetailsItem.this,SummaryActivity.class);
//                Intent intent = new Intent(DetailsItem.this,CartActivity.class);
//                startActivity(intent);
//                finish();

                addedToCart();



//              saving data to database and to cart activivity
                //SaveData();
            }
        });
    }

    public void increment(View view) {
        //price
        float basePrice = Float.parseFloat(realPrice);
        count++;
        quantity.setText("" + count +" kg");
        float total = count * basePrice;
        String setTotalPrice = String.valueOf(total);
        totalPrice.setText(setTotalPrice);
    }

    public void decrement(View view) {
        if(count <= 0){
            Toast.makeText(DetailsItem.this, "Can't decrease quantity < 0", Toast.LENGTH_SHORT).show();
            count = 0;
        }
        else {
            float basePrice = Float.parseFloat(realPrice);
            count--;
            quantity.setText("" + count + " kg");
            float total = count * basePrice;
            String setTotalPrice = String.valueOf(total);
            totalPrice.setText(setTotalPrice);
        }
    }



    private void addedToCart() {
        final HashMap<String,Object> cartMap = new HashMap<>();
        cartMap.put("productName",details_name.getText().toString());
        cartMap.put("productPrice",details_price.getText().toString());
        cartMap.put("Quantity",quantity.getText().toString());
        cartMap.put("productImage",imageUri.toString());
        cartMap.put("TotalPrice",Float.valueOf(totalPrice.getText().toString()));

        fStore.collection("AddToCart").document(fAuth.getCurrentUser().getUid())
                .collection("UserOrder").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(DetailsItem.this, "Added To cart", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }

//    private boolean SaveData() {
//        String name = details_name.getText().toString();
//        String price = details_price.getText().toString();
//        String quantities = String.valueOf(count);
//        String image = img;;
//
//
//        ContentValues values = new ContentValues();
//        values.put(OrderContract.OrderEntry.COLUMN_NAME,name);
//        values.put(OrderContract.OrderEntry.COLUMN_PRICE,price);
//        values.put(OrderContract.OrderEntry.COLUMN_IMAGE,image);
//        values.put(OrderContract.OrderEntry.COLUMN_QUANTITY,quantities);
//
//        if (mCurrentCartUri == null) {
//            Uri newUri = getContentResolver().insert(OrderContract.OrderEntry.CONTENT_URI, values);
//            if (newUri==null) {
//                Toast.makeText(this, "Failed to add to Cart", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(this, "Success adding to Cart", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        hasAllRequiredValues = true;
//        return hasAllRequiredValues;
//
//    }
//
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
//        return new CursorLoader(this, mCurrentCartUri,
//                projection,
//                null,
//                null,
//                null);
//    }
//
//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
//
//        if (cursor == null || cursor.getCount() < 1) {
//            return;
//        }
//
//        if (cursor.moveToFirst()) {
//
//            int name = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_NAME);
//            int price = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_PRICE);
//            int image = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_IMAGE);
//            int quantities = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_QUANTITY);
//
//
//            String cartName = cursor.getString(name);
//            String cartPrice = cursor.getString(price);
//            String productImage = cursor.getString(image);
//            String cartQuantity = cursor.getString(quantities);
//
//            details_name.setText(cartName);
//            details_price.setText(cartPrice);
//            quantity.setText(cartQuantity);
////            String img;
////            img = productImage;
//            String imageUri = null;
//            imageUri = productImage;
//            Picasso.get().load(imageUri).into(details_image);
//        }
//
//
//    }
//
//    @Override
//    public void onLoaderReset( Loader<Cursor> loader) {
//        details_name.setText("");
//        details_price.setText("");
//        quantity.setText("");
////        String img;
////        img = "";
////        String imageUri = null;
////        imageUri = img;
////        Picasso.get().load(imageUri).into(details_image);
//        details_image.setImageResource(0);
//    }
}