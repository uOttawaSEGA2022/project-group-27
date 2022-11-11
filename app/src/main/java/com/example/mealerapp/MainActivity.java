package com.example.mealerapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView signUp;
    private Button btnSignIn;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    private EditText editTextEmail, editTextPassword;
    private String email;
    private String password;

    private Proxy proxy;
    



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        signUp = (TextView)findViewById(R.id.signUp);
        signUp.setOnClickListener(this);


        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        editTextEmail = (EditText)findViewById(R.id.editTextLoginEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextLoginPassword);

        proxy = new Proxy();


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.signUp:
                startActivity(new Intent(this, SelectUserType.class));
                break;
            case R.id.btnSignIn:
                login();


        }
    }

    private void login() {
        
        //TODO test proxy login and delete commented code

        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if(proxy.login(email,password))
            startActivity(new Intent(MainActivity.this, landingPage.class));
        else
            Toast.makeText(MainActivity.this, "Invalid Login", Toast.LENGTH_LONG).show();


//        mAuth.signInWithEmailAndPassword(email,password)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful()){
//                            db.collection("users").document(mAuth.getCurrentUser().getUid())
//                                            .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                                        @Override
//                                        public void onSuccess(DocumentSnapshot documentSnapshot) {
//                                            startActivity(new Intent(MainActivity.this, landingPage.class));
//                                        }
//                                    });
//
//                        }else{
//                        }
//                    }
//                });
    }




}

