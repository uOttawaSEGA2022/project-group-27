package com.example.mealerapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.mealerapp.Objects.Administrator;
import com.example.mealerapp.Objects.Client;
import com.example.mealerapp.Objects.Cook;
import com.example.mealerapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AccountInfo extends AppCompatActivity {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private TextView textViewAccountWelcome;
    private TextView textViewAccountDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);


        db = FirebaseFirestore.getInstance();

        String userType = getIntent().getStringExtra("userType");
        String UID = getIntent().getStringExtra("UID");

        textViewAccountDetails = findViewById(R.id.textViewAccountDetails);
        textViewAccountWelcome = findViewById(R.id.textViewAccountWelcome);

        switch(userType){
            case "Cook":
                db.collection("users").document(UID).get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                Cook cook = documentSnapshot.toObject(Cook.class);
                                textViewAccountWelcome.setText("Welcome, " + cook.getFirstName());

                                textViewAccountDetails.setText("Primary Role: " + cook.getRole() +
                                                                "\n Rating: " + cook.getRating() +
                                                                "\n Address: " + cook.getAddress() +
                                                                "\n FullName: " + cook.getFirstName() + " " + cook.getLastName()  +
                                                                "\n Email: " + cook.getEmail());
                            }
                        });
                break;

            case "Client":
                db.collection("users").document(UID).get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                Client client = documentSnapshot.toObject(Client.class);
                                textViewAccountWelcome.setText("Welcome, " + client.getFirstName());

                                textViewAccountDetails.setText("Primary Role: " + client.getRole() +
                                        "\n Address: " + client.getAddress() +
                                        "\n FullName: " + client.getFirstName() + " " + client.getLastName()  +
                                        "\n Email: " + client.getEmail());
                            }
                        });
                break;

            case "Admin":
                db.collection("users").document(UID).get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                Administrator admin = documentSnapshot.toObject(Administrator.class);

                                textViewAccountWelcome.setText("Welcome, " + admin.getFirstName());

                                textViewAccountDetails.setText("Primary Role: " + admin.getRole() +
                                        "\n Address: " + admin.getAddress() +
                                        "\n FullName: " + admin.getFirstName() + " " + admin.getLastName()  +
                                        "\n Email: " + admin.getEmail());
                            }
                        });

                break;



        }






    }
}