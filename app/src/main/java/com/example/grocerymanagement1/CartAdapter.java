package com.example.grocerymanagement1;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.grocerymanagement1.Database.OrderContract;
import com.squareup.picasso.Picasso;

public class CartAdapter extends CursorAdapter {
    public CartAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.mycart_items, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // getting the views
        ImageView productImage;
        TextView cartName, cartPrice, cartQuantity;

        cartName = view.findViewById(R.id.drinkNameinOrderSummary);
        cartPrice = view.findViewById(R.id.priceinOrderSummary);
        cartQuantity = view.findViewById(R.id.quantityinOrderSummary);
        productImage = view.findViewById(R.id.details_image);

        // getting the values by first getting the position of their columns

        int name = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_NAME);
        int price = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_PRICE);
        int image = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_IMAGE);
        int quantity = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_QUANTITY);


        String nameofcart = cursor.getString(name);
        String pricesofcart = cursor.getString(price);
        String productImageofcart = cursor.getString(image);
        String quantitysofcart = cursor.getString(quantity);



        cartName.setText(nameofcart);
        cartPrice.setText(pricesofcart);
        cartQuantity.setText(quantitysofcart);
//        String img;
//        img = productImageofcart;
        String imageUri = null;
        imageUri = productImageofcart;
        Picasso.get().load(imageUri).into(productImage);

        //productImage.setImageResource(productImageofcart);



    }
}
