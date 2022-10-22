package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class CookActivity extends AppCompatActivity implements View.OnClickListener{

    private Button next;
    private EditText editTextFirstName, editTextLastName, editTextEmail, editTextPassword, editTextConfirmPassword, editTextAddress, editTextDesc;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cook);

        next = (Button)findViewById(R.id.nextButton);
        next.setOnClickListener(this);

        editTextFirstName = (EditText) findViewById(R.id.textFirst);
        editTextLastName = (EditText)findViewById(R.id.textLast);
        editTextAddress = (EditText)findViewById(R.id.textAddress);
        editTextEmail = (EditText)findViewById(R.id.textUser);
        editTextPassword = (EditText)findViewById(R.id.textPass);
        editTextConfirmPassword = (EditText)findViewById(R.id.textConfirm);
        editTextDesc = (EditText)findViewById(R.id.textDesc);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case(R.id.nextButton):
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
            editTextEmail.setError("Invalid Email, Email is Required");
            editTextEmail.requestFocus();
            return;
        }

        if(!email.isEmpty()){
            if(!(email.contains("@") && (email.contains(".com") || email.contains(".ca")))){
                editTextEmail.setError("Invalid Email, Email is Required");
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
                                    desc
                            );

                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(cook).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(CookActivity.this,
                                                        "Registeration Succussful",
                                                        Toast.LENGTH_LONG).show();
                                            }else{
                                                Toast.makeText(CookActivity.this,
                                                        "Registeration Failed" + task.getException().getMessage(),
                                                        Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        }else{
                            Toast.makeText(CookActivity.this,
                                    "Registeration Failed" + task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
        startActivity(new Intent(CookActivity.this, MainActivity.class));

    }
}