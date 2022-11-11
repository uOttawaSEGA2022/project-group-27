package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class AddPaymentMethod extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private Button completeSignUp;

    private EditText nameOnCard, cardNumber, expirationDate, CVV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment_method);

        completeSignUp = (Button)findViewById(R.id.completeSignUp);
        completeSignUp.setOnClickListener(this);

        nameOnCard = (EditText)findViewById(R.id.nameOnCard);
        cardNumber = (EditText) findViewById(R.id.cardNumber);
        expirationDate = (EditText)findViewById(R.id.expirationDate);
        CVV = (EditText) findViewById(R.id.CVV);

        mAuth = FirebaseAuth.getInstance();

    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.completeSignUp:
//                registerUser();
                break;
        }
    }


}