package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectUserType extends AppCompatActivity implements View.OnClickListener{

    private Button cook;
    private Button client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user_type);

        cook = (Button) findViewById(R.id.cook);
        cook.setOnClickListener(this);
        client = (Button) findViewById(R.id.client);
        client.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.cook:
                startActivity(new Intent(this, CookActivity.class));
                break;
            case R.id.client:
                startActivity(new Intent(this, ClientActivity.class));
                break;
        }
    }
}