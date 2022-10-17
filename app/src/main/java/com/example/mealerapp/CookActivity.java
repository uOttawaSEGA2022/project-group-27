package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CookActivity extends AppCompatActivity {

    String first,last,address,email,password,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cook);
    }

    public void onNext(View view){
        first = findViewById(R.id.textFirst).toString();
        last = findViewById(R.id.textLast).toString();
        address = findViewById(R.id.textAddress).toString();
        email = findViewById(R.id.textUser).toString();
        description = findViewById(R.id.textDesc).toString();
        if(findViewById(R.id.textPass) == findViewById(R.id.textConfirm)){
            password = findViewById(R.id.textPass).toString();
        }
    }

}