package com.example.mealerapp;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Complaints {
    public Cook complained_cook;  //added by Carl because the activity is needed
    public String description;
    public  String _id;

    public Complaints(String id, Cook target_cook, String description){
        this._id = id;
        this.complained_cook = target_cook;
        this.description = description;
    }

    public Cook get_Cook(){ return complained_cook; }
    public String getDescription(){ return description; }
    public void setId(String id) {
        _id = id;
    }
    public String getId() {
        return _id;
    }
    public String getName() { return complained_cook.getFirstName() + complained_cook.getLastName(); }



}
