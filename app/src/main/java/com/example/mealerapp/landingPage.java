package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class landingPage extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogout;
    private TextView textViewWelcome;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseRef;

    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(this);

        textViewWelcome = (TextView) findViewById(R.id.textViewWelcome);

        mAuth = FirebaseAuth.getInstance();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("users");
        userID = mAuth.getCurrentUser().getUid();

        mDatabaseRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);

                if(user != null){
                    textViewWelcome.setText("Welcome " + user.getFirstName() + "! You are logged in as a " + user.getRole() +
                                            ". Your Email is " + user.getEmail() + " and your Address is " + user.getAddress());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(landingPage.this, "Error Occured", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnLogout:
                mAuth.signOut();
                startActivity(new Intent(this, MainActivity.class));
        }
    }
}