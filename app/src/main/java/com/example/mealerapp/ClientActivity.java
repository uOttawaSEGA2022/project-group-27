package com.example.mealerapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class ClientActivity extends AppCompatActivity implements View.OnClickListener{

    private Button addPayment;
    private EditText editTextFirstName, editTextLastName, editTextAddress, editTextEmail, editTextPassword, editTextConfirmPassword;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);



        addPayment = (Button)findViewById(R.id.addPayment);
        addPayment.setOnClickListener(this);

        editTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
        editTextLastName = (EditText) findViewById(R.id.editTextLastName);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        db = FirebaseFirestore.getInstance();

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.addPayment:
                registerUser();
                break;
        }
    }

    private void registerUser() {

        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();
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
                            CreditCard cc = new CreditCard("name",1234567890, "10/22", 424);
                            Client client = new Client(
                                    "Client",
                                    firstName,
                                    lastName,
                                    address,
                                    email,
                                    password
                            );

                            db.collection("users").document(mAuth.getCurrentUser().getUid())
                                    .set(client).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(ClientActivity.this,
                                                    "Registration Successful",
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(ClientActivity.this,
                                                    "Registration Failed " + task.getException().getMessage(),
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    });

                            db.collection("users").document(mAuth.getCurrentUser().getUid()).collection("creditCards")
                                    .add(cc)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Toast.makeText(ClientActivity.this, "Added Payment Method Successfully", Toast.LENGTH_LONG).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(ClientActivity.this,
                                                    "Card Failed " + task.getException().getMessage(),
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    });

                        }else{
                            Toast.makeText(ClientActivity.this,
                                    "Registeration Failed " + task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
        startActivity(new Intent(ClientActivity.this, MainActivity.class));

    }
}