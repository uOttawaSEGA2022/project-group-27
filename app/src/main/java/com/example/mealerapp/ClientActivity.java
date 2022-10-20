package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.LinkedList;

public class ClientActivity extends AppCompatActivity implements View.OnClickListener{

    private Button addPayment;
    private EditText name, address, email, password, confirmPassword;
    private String[] textFields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        addPayment = (Button)findViewById(R.id.addPayment);
        addPayment.setOnClickListener(this);

        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);

        textFields =  new String[]{name.toString(),address.toString(),email.toString(), password.toString(), confirmPassword.toString()};
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.addPayment:
                Intent intent = new Intent(this, AddPaymentMethod.class);
                intent.putExtra("textFields", textFields);
                startActivity(intent);
                break;
        }
    }
}