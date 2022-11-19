package com.example.mealerapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mealerapp.R;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuth;

public class Suspended extends AppCompatActivity {

    private TextView textViewSuspensionTime;

    private Button btnLogOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspended);

        Intent intent = getIntent();

        String until = intent.getStringExtra("until");


        textViewSuspensionTime = findViewById(R.id.suspensionTime);
        btnLogOut = findViewById(R.id.btnSuspendedLogout);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Suspended.this, MainActivity.class));
            }
        });

        if(until == null)
            textViewSuspensionTime.setText("This suspension is permenant");
        else
            textViewSuspensionTime.setText("Until: " + until);




    }
}