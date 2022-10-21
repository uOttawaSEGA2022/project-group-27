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
                startActivity(new Intent(this, MainActivity.class));
                break;

        }
    }
    private void registerUser() {

        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString();
        String desc = editTextDesc.getText().toString();

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Cook cook = new Cook(
                                    "cook",
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
    }
}