package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class CookActivity extends AppCompatActivity implements View.OnClickListener{

    private Button next;
    private EditText editTextFirstName, editTextLastName, editTextEmail, editTextPassword, editTextConfirmPassword, editTextAddress, editTextDesc;
    private FirebaseAuth mAuth;


    private DatabaseReference mDatabase;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cook);

        next = (Button)findViewById(R.id.btnNext);
        next.setOnClickListener(this);

        editTextFirstName = (EditText) findViewById(R.id.editTextFirstNameCook);
        editTextLastName = (EditText)findViewById(R.id.editTextLastNameCook);
        editTextAddress = (EditText)findViewById(R.id.editTextAddressCook);
        editTextEmail = (EditText)findViewById(R.id.editTextEmailCook);
        editTextPassword = (EditText)findViewById(R.id.editTextPasswordCook);
        editTextConfirmPassword = (EditText)findViewById(R.id.editTextConfirmPasswordCook);
        editTextDesc = (EditText)findViewById(R.id.editTextDescription);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        db = FirebaseFirestore.getInstance();

    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case(R.id.btnNext):
                registerUser();
                break;

        }
    }
    private void registerUser() {

        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();
        String desc = editTextDesc.getText().toString();

        if(firstName.isEmpty()){
            editTextFirstName.setError("Name is Required");
            editTextFirstName.requestFocus();
            return;
        }
        if(lastName.isEmpty()){
            editTextLastName.setError("Name is Required");
            editTextLastName.requestFocus();
            return;
        }
        if(address.isEmpty()){
            editTextAddress.setError("Address is Required");
            editTextAddress.requestFocus();
            return;
        }
        if(email.isEmpty()){
            editTextEmail.setError("Invalid Email");
            editTextEmail.requestFocus();
            return;
        }

        if(!email.isEmpty()){
            if(!(email.contains("@") && (email.contains(".com") || email.contains(".ca")))){
                editTextEmail.setError("Invalid Email");
                editTextEmail.requestFocus();
                return;
            }
        }

        if(desc.isEmpty()){
            editTextDesc.setError("Description is Required");
            editTextDesc.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is Required");
            editTextPassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            editTextPassword.setError("Password must be at least 6 characters");
            editTextPassword.requestFocus();
            return;
        }
        if(confirmPassword.isEmpty()){
            editTextConfirmPassword.setError("Please Confirm Password");
            editTextConfirmPassword.requestFocus();
            return;
        }

        if(!confirmPassword.equals(password)){
            editTextConfirmPassword.setError("Passwords do not match");
            editTextConfirmPassword.requestFocus();
            return;
        }

//        if(proxy.registerUser(email, password)){
//            Cook cook = new Cook(
//                    "Cook",
//                    firstName,
//                    lastName,
//                    address,
//                    email,
//                    password,
//                    desc,
//                   proxy.getCurrentUserUID()
//            );
//
//            if(proxy.saveUser(cook)){
//                Toast.makeText(
//                        CookActivity.this,
//                        "Registration Successful",
//                        Toast.LENGTH_LONG
//                ).show();
//
//                if(proxy.login(email, password)){
//                    startActivity(new Intent(CookActivity.this, landingPage.class));
//                }
//
//
//            }

//        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Cook cook = new Cook(
                                    "Cook",
                                    firstName,
                                    lastName,
                                    address,
                                    email,
                                    password,
                                    desc,
                                    mAuth.getCurrentUser().getUid()
                            );

                            db.collection("users").document(mAuth.getCurrentUser().getUid())
                                            .set(cook).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(CookActivity.this,
                                                    "Registration Successful",
                                                    Toast.LENGTH_LONG).show();

                                            mAuth.signInWithEmailAndPassword(email, password)
                                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                                            startActivity(new Intent(CookActivity.this, landingPage.class));
                                                        }
                                                    });
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(CookActivity.this,
                                                    "Registration Failed",
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    });
                        }else{
                            Toast.makeText(CookActivity.this,
                                    "Registration Failed" + task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
        startActivity(new Intent(CookActivity.this, MainActivity.class));

    }
}