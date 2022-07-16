package com.example.grocerymanagement1.AdminFiles;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.grocerymanagement1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadProduct extends AppCompatActivity {
//    Button addProduct;
//    private EditText in_productname, in_productprice,in_productrating;
//    private String productname,productrating;
//    private int productprice;
//    private String URL = "http://10.0.2.2/login/insert.php";
    Button browse;
    Bitmap bitmap;
    String encodeImageString;

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    ImageView img;
    EditText editText1,editText2,editText3;
    Button addProduct;
    private static final int Gallery_code = 1;
    Uri imageUrl = null;
    ProgressDialog progressDialog;

    String whichProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        img= findViewById(R.id.img);
        editText1 =findViewById(R.id.editText1);
        editText2 =findViewById(R.id.editText2);
        editText3 =findViewById(R.id.editText3);

        addProduct = findViewById(R.id.addData);

        mDatabase =FirebaseDatabase.getInstance();
        whichProduct=getIntent().getStringExtra("mRef");
        Toast.makeText(this, "From "+whichProduct, Toast.LENGTH_SHORT).show();
        mRef = mDatabase.getReference().child(whichProduct);
        mStorage = FirebaseStorage.getInstance();
        progressDialog = new ProgressDialog(this);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/");
                startActivityForResult(intent,Gallery_code);
            }
        });


    }

    //mee


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Gallery_code && resultCode==RESULT_OK)
        {
            imageUrl = data.getData();
            img.setImageURI(imageUrl);
        }
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pr_name = editText1.getText().toString().trim();
                String pr_price= editText2.getText().toString().trim();
                String pr_desc = editText3.getText().toString().trim();
                if(!(pr_name.isEmpty() && pr_price.isEmpty() && pr_desc.isEmpty() && imageUrl!=null)) {

                    progressDialog.setTitle("Uploading...");
                    progressDialog.show();

                    StorageReference filepath = mStorage.getReference().child("FruitImages").child(imageUrl.getLastPathSegment());
                    filepath.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String t = task.getResult().toString();
                                    DatabaseReference newPost = mRef.push();
                                    newPost.child("productName").setValue(pr_name);
                                    newPost.child("productPrice").setValue(pr_price);
                                    newPost.child("productDesc").setValue(pr_desc);
                                    newPost.child("image").setValue(task.getResult().toString());
                                    progressDialog.dismiss();

                                    Intent intent = new Intent(UploadProduct.this, Admin.class);
                                    Toast.makeText(UploadProduct.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                }
            }
        });
    }

//    private void encodeBitmapImage(Bitmap bitmap) {
//        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
//        byte[] bytesofimage=byteArrayOutputStream.toByteArray();
//        encodeImageString=android.util.Base64.encodeToString(bytesofimage, Base64.DEFAULT);
//    }

    //mee



//    public void save(View view) {
//        productname = in_productname.getText().toString().trim();
//        productprice = Integer.parseInt(in_productprice.getText().toString().trim());
//        productrating = in_productrating.getText().toString().trim();
//
//        if(!productname.equals("") && !(productprice ==0) && !productrating.equals("")){
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    if (response.equals("success")) {
//                        in_productname.setText("");
//                        in_productprice.setText("");
//                        in_productrating.setText("");
//                        img.setImageResource(R.drawable.ic_launcher_foreground);
//                        Toast.makeText(UploadProduct.this, "Successfully added", Toast.LENGTH_SHORT).show();
//                        //finish();
//                        //addProduct.setClickable(false);
//                    } else if (response.equals("failure")) {
//                        Toast.makeText(UploadProduct.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
//                }
//            }){
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    Map<String, String> data = new HashMap<>();
//                    data.put("productname", productname);
//                    data.put("productprice", String.valueOf(productprice));
//                    data.put("productrating", productrating);
//                    data.put("image",encodeImageString);
//                    return data;
//                }
//            };
//            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//            requestQueue.add(stringRequest);
//        }else{
//            Toast.makeText(this, "Fields can not be empty!", Toast.LENGTH_SHORT).show();
//        }
//
//    }

}