package com.example.mealerapp;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Complaints {
    private Cook complained_cook;  //added by Carl because the activity is needed
    private String details;

    public Complaints(Cook target_cook, String details){
        this.complained_cook = target_cook;
        this.details = details;
    }

    public Cook get_Cook(){ return this.complained_cook; }
    public String getDetails(){ return this.details; }

//there should be a list of complaints that the admin can view and it is added to the database
//with an add_complaints() method, each time a client makes a complaint, it's added here


}
