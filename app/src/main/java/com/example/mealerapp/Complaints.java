package com.example.mealerapp;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Complaints {
    public Cook complained_cook;  //added by Carl because the activity is needed
    public String description;

    public Complaints(Cook target_cook, String description){
        this.complained_cook = target_cook;
        this.description = description;
    }

    public Cook get_Cook(){ return complained_cook; }
    public String getDescription(){ return description; }



}
