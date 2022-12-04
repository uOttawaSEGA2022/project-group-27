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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

public class Suspended extends AppCompatActivity {

    private TextView textViewSuspensionTime;

    private Button btnLogOut;

    private FirebaseAuth mAuth;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspended);

        Intent intent = getIntent();

//        Date until = (Date) intent.getSerializableExtra("until");
        Date until = new Date(intent.getLongExtra("until",0));


        textViewSuspensionTime = findViewById(R.id.suspensionTime);
        btnLogOut = findViewById(R.id.btnSuspendedLogout);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Suspended.this, MainActivity.class));
            }
        });

//        if(until == null)
//            textViewSuspensionTime.setText("This suspension is permanent");
//        else
//            textViewSuspensionTime.setText("Until: " + until);
        textViewSuspensionTime.setText(until != null ? "Until: " + until : "This suspension is permanent.");
    }
}