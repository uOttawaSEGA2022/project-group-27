package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;

public class landingPage extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout drawer;

    //we should edit this to say if we got the admin credentials we will land on adminLanding
    //and we need a landing for cook and a landing for client since each will have diff services

    private Button btnLogout;
    private TextView textViewWelcome;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseRef;

    private FirebaseFirestore db;

    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(this);

        textViewWelcome = (TextView) findViewById(R.id.textViewWelcome);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        mAuth = FirebaseAuth.getInstance();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("users");
        userID = mAuth.getCurrentUser().getUid();

        db = FirebaseFirestore.getInstance();

        db.collection("users")
                        .document(mAuth.getCurrentUser().getUid())
                                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        User user = documentSnapshot.toObject(User.class);
                        if(user.getRole().equals("Admin")){
                            Administrator admin = new Administrator(
                                    user.getRole(),
                                    user.getFirstName(),
                                    user.getLastName(),
                                    user.getAddress(),
                                    user.getEmail(),
                                    user.getPassword(),
                                    user.getUID()
                            );
                            db.collection("users").document(admin.getUID())
                                    .collection("complaints")
                                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                           if(task.isSuccessful()){
                                               for(QueryDocumentSnapshot document : task.getResult()){

                                               }
                                           }
                                        }
                                    });
                        }
                        if(user.getSuspended() == true){
                            if(user.until == null){
                                Toast.makeText(landingPage.this, "Your account has been permanently suspended", Toast.LENGTH_LONG).show();
                            }else if(Calendar.getInstance().after(user.getUntil())){
                                user.suspended = false;
                                user.until = null;
                            }else{
                            Toast.makeText(landingPage.this, "Your account has been suspended until " + user.until, Toast.LENGTH_LONG).show();
                            }
                        }else{
                            textViewWelcome.setText("Welcome " + user.getFirstName() + "! You are logged in as a " + user.getRole() +
                                ". Your Email is " + user.getEmail() + " and your Address is " + user.getAddress());
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });


//        mDatabaseRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                User user = snapshot.getValue(User.class);
//
//                if(user != null){
//                    if(user.getRole() == "Admin"){
//                        Administrator admin = new Administrator("Admin", user.getFirstName(), user.getLastName(), user.getAddress(), user.getEmail(), user.getPassword());
//
//                        startActivity(new Intent(landingPage.this, AdminScreen.class));
//
//                    }
//                    if(user.getSuspended() == true){
//                        if(user.until == null){
//                            Toast.makeText(landingPage.this, "Your account has been permanently suspended", Toast.LENGTH_LONG).show();
//                        }else if(Calendar.getInstance().after(user.getUntil())){
//                            user.suspended = false;
//                            user.until = null;
//                        }else{
//                            Toast.makeText(landingPage.this, "Your account has been suspended until " + user.until, Toast.LENGTH_LONG).show();
//                        }
//                    }else{
//                        textViewWelcome.setText("Welcome " + user.getFirstName() + "! You are logged in as a " + user.getRole() +
//                                ". Your Email is " + user.getEmail() + " and your Address is " + user.getAddress());
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(landingPage.this, "Error Occurred", Toast.LENGTH_LONG).show();
//            }
//        });

    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }

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