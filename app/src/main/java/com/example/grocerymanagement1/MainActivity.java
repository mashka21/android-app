package com.example.grocerymanagement1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.grocerymanagement1.AdminFiles.Admin;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
//    Button btnlogin;
//    private EditText inputEmail, inputPassword;
//    private String email,password;
//    private String URL = "http://10.0.2.2/login/login.php";

/* firebase starts*/
    EditText email, password;
    Button loginBtn;
    boolean valid = true;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        /*firebase*/
        email = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);
        loginBtn = findViewById(R.id.btnlogin);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkField(email);
                checkField(password);

                if(valid){
                    fAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(MainActivity.this, "Succesfull login", Toast.LENGTH_SHORT).show();
                            checkUserLevel(authResult.getUser().getUid());
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "invalid: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });




//        email = password = "";
//        inputEmail = findViewById(R.id.inputEmail);
//        inputPassword = findViewById(R.id.inputPassword);

    }

    private void checkUserLevel(String uid){
        DocumentReference df = fStore.collection("Users").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG", "onSuccess: "+documentSnapshot.getData());

                //identify the user access level

                if(documentSnapshot.getString("isAdmin") != null){
                    //user is Admin
                    startActivity(new Intent(getApplicationContext(), Admin.class));
                    finish();
                }

                if(documentSnapshot.getString("isUser") != null){
                    //user is NOT Admin
                    startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
                    finish();
                }


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Sorry, invalid login", Toast.LENGTH_SHORT).show();
            }
        });
    }
    /*firebase*/
    public boolean checkField(EditText textField) {
        if(textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid = false;
        }else {
            valid = true;
        }

        return valid;
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            DocumentReference df = FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
            df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if(documentSnapshot.getString("isAdmin") != null){
                        startActivity(new Intent(getApplicationContext(),Admin.class));
                        finish();
                    }
                    if(documentSnapshot.getString("isUser") != null){
                        startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, "something wrong", Toast.LENGTH_SHORT).show();
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }
            });
        }
    }

    //    public void login(View view) {
//        email = inputEmail.getText().toString().trim();
//        password = inputPassword.getText().toString().trim();
//        if(!email.equals("") && !password.equals("")){
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    Log.d("res", response);
//                    if (response.equals("success")) {
//                        Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
//                        startActivity(intent);
//                        finish();
//                    } else if (response.equals("failure")) {
//                        Toast.makeText(MainActivity.this, "Invalid Login Id/Password", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(MainActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
//                }
//            }){
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    Map<String, String> data = new HashMap<>();
//                    data.put("email", email);
//                    data.put("password", password);
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

    public void register(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

}