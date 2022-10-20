package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CookActivity extends AppCompatActivity implements View.OnClickListener{

    private Button next;
    private EditText first, last, email, pass, confirm, address, desc;
    private EditText[] textFields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cook);

        next = (Button)findViewById(R.id.nextButton);
        next.setOnClickListener(this);

        first = (EditText) findViewById(R.id.textFirst);
        last = (EditText)findViewById(R.id.textLast);
        address = (EditText)findViewById(R.id.textAddress);
        email = (EditText)findViewById(R.id.textUser);
        pass = (EditText)findViewById(R.id.textPass);
        confirm = (EditText)findViewById(R.id.textConfirm);
        desc = (EditText)findViewById(R.id.textDesc);

        textFields = new EditText[]{first,last,address,email,pass,confirm};
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case(R.id.nextButton):
                Cook cook = new Cook(UserRole.COOK, first.toString(),address.toString(),last.toString(),email.toString(),pass.toString(),desc.toString());

        }
    }
}