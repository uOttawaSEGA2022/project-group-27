package com.example.mealerapp.Activity;


import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealerapp.Objects.Cook;
import com.example.mealerapp.Objects.User;
import com.example.mealerapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView signUp;
    private Button btnSignIn;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

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
        db = FirebaseFirestore.getInstance();
        editTextEmail = (EditText)findViewById(R.id.editTextLoginEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextLoginPassword);


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.signUp:
                try{
                    startActivity(new Intent(this, SelectUserType.class));

                }catch(Exception e){
                    Toast.makeText(MainActivity.this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnSignIn:
                login();
                break;


        }
    }

    private void login() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        try{
            mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                db.collection("users").document(mAuth.getCurrentUser().getUid())
                                        .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                            @Override
                                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                User user = documentSnapshot.toObject(User.class);
                                                if(user.getRole().equals("Cook")){
                                                    Cook cook = documentSnapshot.toObject(Cook.class);
                                                    if(cook.isSuspended()){
                                                        Calendar c = Calendar.getInstance();
                                                        if(cook.getUntil().before(c.getTime())){
                                                            cook.setUntil(null);
                                                            cook.setSuspended(false);
                                                            startActivity(new Intent(MainActivity.this, landingPage.class));
                                                        }
                                                        Intent intent = new Intent(MainActivity.this, Suspended.class);
                                                        try{
                                                            intent.putExtra("until", cook.getUntil().getTime());

                                                        }catch(Exception e){
                                                            intent.putExtra("until", (String) null);
                                                        }
                                                        startActivity(intent);
                                                    }else{
                                                        startActivity(new Intent(MainActivity.this, landingPage.class));
                                                    }
                                                }else{
                                                    startActivity(new Intent(MainActivity.this, landingPage.class));
                                                }
                                            }
                                        });

                            }else{
                                Toast.makeText(MainActivity.this, "Invalid Login", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }catch(Exception e){
            Toast.makeText(MainActivity.this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }

    }




}

