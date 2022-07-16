package com.example.grocerymanagement1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

    Context context;
    List<MyCartModel> list;
    int totalAmount=0;

    public MyCartAdapter(Context context, List<MyCartModel> list){
        this.context =context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mycart_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(list.get(position).getProductName());
        holder.price.setText(list.get(position).getProductPrice());
        holder.quantity.setText(list.get(position).getQuantity());
        String imageUri = null;
        imageUri = list.get(position).productImage;
        Picasso.get().load(imageUri).into(holder.image);

        //Total amount pass to cartActivity
        totalAmount = totalAmount+list.get(position).getTotalPrice();
        Intent intent = new Intent("MyTotalAmount");
        intent.putExtra("totalAmount",totalAmount);
        //Toast.makeText(context, "amount: "+totalAmount, Toast.LENGTH_SHORT).show();

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,price,quantity,totalPrice;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.drinkNameinOrderSummary);
            price = itemView.findViewById(R.id.priceinOrderSummary);
            quantity = itemView.findViewById(R.id.quantityinOrderSummary);
            image = itemView.findViewById(R.id.details_image);
        }
    }
}
