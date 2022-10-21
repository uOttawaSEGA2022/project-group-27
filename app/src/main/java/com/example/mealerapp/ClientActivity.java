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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.LinkedList;

public class ClientActivity extends AppCompatActivity implements View.OnClickListener{

    private Button addPayment;
    private EditText editTextName, editTextAddress, editTextEmail, editTextPassword, editTextConfirmPassword;
    private String[] textFields;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);



        addPayment = (Button)findViewById(R.id.addPayment);
        addPayment.setOnClickListener(this);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);

        mAuth = FirebaseAuth.getInstance();

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
//        String[] textFields = getIntent().getExtras().getStringArray("textFields");

        String name = editTextName.getText().toString();
        String address = editTextAddress.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

//        CreditCard cc = new CreditCard(
//                nameOnCard.toString(),
//                Long.parseLong(cardNumber.toString()),
//                expirationDate.toString(),
//                Integer.parseInt(CVV.toString())
//        );


        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Client client = new Client(
                                    name,
                                    address,
                                    email,
                                    password
                            );

                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(client).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(ClientActivity.this,
                                                        "Registeration Succussful",
                                                        Toast.LENGTH_LONG).show();
                                            }else{
                                                Toast.makeText(ClientActivity.this,
                                                        "Registeration Failed",
                                                        Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        }else{
                            Toast.makeText(ClientActivity.this,
                                    "Registeration Failed",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}