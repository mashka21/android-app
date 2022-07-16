package com.example.grocerymanagement1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    ArrayList<ProductModelClass> products;
    Context context;

    public void  setFilteredList(ArrayList<ProductModelClass> filteredList) {
        this.products = filteredList;
        notifyDataSetChanged();
    }

    public Adapter(ArrayList<ProductModelClass> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ProductModelClass product = products.get(position);
        holder.productimage.setImageResource(product.getproductImage());
        holder.name.setText(product.getproductName());
        holder.price.setText(String.valueOf(product.getproductpricee()));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, DetailsItem.class);
                intent.putExtra("image", product.getproductImage());
                intent.putExtra("name", product.getproductName());
                intent.putExtra("price", product.getproductpricee());
                intent.putExtra("Description", product.getDescription());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productimage;
        TextView name, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productimage = itemView.findViewById(R.id.itemImage);
            name = itemView.findViewById(R.id.itemName);
            price = itemView.findViewById(R.id.itemPrice);

        }
    }
}