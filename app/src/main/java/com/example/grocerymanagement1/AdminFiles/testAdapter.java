package com.example.grocerymanagement1.AdminFiles;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grocerymanagement1.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class testAdapter extends FirebaseRecyclerAdapter<AdminModel,testAdapter.ViewHolder> {

    Context context;
    public testAdapter(@NonNull FirebaseRecyclerOptions<AdminModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull AdminModel model) {

        getRef(position).getKey();
        holder.name.setText(model.getProductName());
        holder.price.setText(model.getProductPrice());
        String imageUri = null;
        imageUri = model.getimage();
        Picasso.get().load(imageUri).into(holder.imageView);

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogPlus dialogPlus = DialogPlus.newDialog(context)
                        .setGravity(Gravity.CENTER)
                        .setMargin(50,0,50,0)
                        .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.editproduct))
                        .setExpanded(false)
                        .create();

                View holdeView = (LinearLayout) dialogPlus.getHolderView();
                EditText pr_name = holdeView.findViewById(R.id.pr_name);
                EditText pr_price = holdeView.findViewById(R.id.pr_price);
                EditText pr_desc = holdeView.findViewById(R.id.pr_desc);
                ImageView pr_image = holdeView.findViewById(R.id.pr_image);

                pr_name.setText(model.getProductName());
                pr_price.setText(model.getProductPrice());
                pr_desc.setText(model.getProductDesc());
                String imageUri = null;
                imageUri = model.getimage();
                Picasso.get().load(imageUri).into(pr_image);

                Button update_pr = holdeView.findViewById(R.id.update_pr);
                update_pr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("productName",pr_name.getText().toString());
                        map.put("productPrice",pr_price.getText().toString());
                        map.put("productDesc",pr_desc.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Fruits")
                                .child(getRef(position).getKey())
                                .updateChildren(map)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
                dialogPlus.show();
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "From" +context, Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Are you sure!");
                builder.setMessage("Deleted data can't undo.");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Fruits")
                                .child(getRef(position).getKey())
                                .removeValue()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(context, "Deleted Successfully...", Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "Canceled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();

            }
        });


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_design_item,parent,false);
        return new testAdapter.ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,price,description;
        TextView delete,update;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.productImage);
            name = itemView.findViewById(R.id.productName);
            price = itemView.findViewById(R.id.productPrice);

            delete = itemView.findViewById(R.id.delete);
            update = itemView.findViewById(R.id.update);
        }
    }
}
