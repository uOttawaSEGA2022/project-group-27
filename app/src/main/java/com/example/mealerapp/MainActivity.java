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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView signUp;
    private Button btnSignIn;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private FirebaseUser currentUser;

    private EditText editTextEmail, editTextPassword;
    private String email;
    private String password;

    



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        signUp = (TextView)findViewById(R.id.signUp);
        signUp.setOnClickListener(this);


        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        editTextEmail = (EditText)findViewById(R.id.editTextLoginEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextLoginPassword);


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
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            currentUser = mAuth.getCurrentUser();
                            startActivity(new Intent(MainActivity.this, landingPage.class));

                        }else{
                            Toast.makeText(MainActivity.this, "Invalid Login", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }




}

