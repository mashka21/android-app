package com.example.grocerymanagement1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
//    private EditText inputUsername,inputEmail;
//    private EditText inputPassword;
//    private EditText inputConformPassword;
//    private Button btnRegister;
//    TextView tvStatus;
//    private String  URL = "http://10.0.2.2/login/register.php";
//    private String username;
//    private String email;
//    private String password;
//    private String confirmPassword;

    /*firebase*/
    EditText username,email,password,phone;
    TextView goToLogin;
    Button registerBtn;
    boolean valid = true;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        /*firebase*/
        username = findViewById(R.id.inputUsername);
        email = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);
        phone = findViewById(R.id.phone);
        registerBtn = findViewById(R.id.btnRegister);
        goToLogin = findViewById(R.id.alreadyHaveAccount);


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkField(username);
                checkField(email);
                checkField(password);
                checkField(phone);

                if(valid){
                    //starting user register
                    fAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser user = fAuth.getCurrentUser();
                            Toast.makeText(RegisterActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                            DocumentReference df = fStore.collection("Users").document(user.getUid());
                            Map<String,Object> userInfo = new HashMap<>();
                            userInfo.put("username",username.getText().toString());
                            userInfo.put("email",email.getText().toString());
                            userInfo.put("phone",phone.getText().toString());
                            userInfo.put("image",R.drawable.googleg_standard_color_18);
                            //specify if the user is admin
                            userInfo.put("isUser", "1");

                            df.set(userInfo);


                            startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegisterActivity.this, "Failed to : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });



//        TextView btn=findViewById(R.id.alreadyHaveAccount);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
//                finish();
//            }
//        });


//        inputUsername = findViewById(R.id.inputUsername);
//        inputEmail = findViewById(R.id.inputEmail);
//        inputPassword = findViewById(R.id.inputPassword);
//        inputConformPassword = findViewById(R.id.inputConformPassword);
//        tvStatus = findViewById(R.id.tvStatus);
//        btnRegister =findViewById(R.id.btnRegister);
//        username = email = password = confirmPassword="";

    }


    /*firebase*/
    public boolean checkField(EditText textField){
        if(textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid = false;
        }else {
            valid = true;
        }

        return valid;
    }


//    public void save(View view) {
//        username = inputUsername.getText().toString().trim();
//        email = inputEmail.getText().toString().trim();
//        password = inputPassword.getText().toString().trim();
//        confirmPassword = inputConformPassword.getText().toString().trim();
//        if(!password.equals(confirmPassword)){
//            Toast.makeText(this, "Password Mismatch", Toast.LENGTH_SHORT).show();
//        }
//        else if(!username.equals("") && !email.equals("") && !password.equals("")){
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    if (response.equals("success")) {
//                        tvStatus.setText("Successfully registered.");
//                        btnRegister.setClickable(false);
//                    } else if (response.equals("failure")) {
//                        tvStatus.setText("Something went wrong.");
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
//                    data.put("username", username);
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
}
