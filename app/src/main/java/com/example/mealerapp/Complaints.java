package com.example.mealerapp;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Complaints {
<<<<<<< HEAD
    public Cook complained_cook;  //added by Carl because the activity is needed
    public String description;
    public  String _id;

    public Complaints(String id, Cook target_cook, String description){
        this._id = id;
=======
    private Cook complained_cook;  //added by Carl because the activity is needed
    private String details;

    public Complaints(Cook target_cook, String details){
>>>>>>> e969f3f33f542ff857d69322cb88b700bdd2de3d
        this.complained_cook = target_cook;
        this.details = details;
    }

<<<<<<< HEAD
    public Cook get_Cook(){ return complained_cook; }
    public String getDescription(){ return description; }
    public void setId(String id) {
        _id = id;
    }
    public String getId() {
        return _id;
    }
    public String getName() { return complained_cook.getFirstName() + complained_cook.getLastName(); }
=======
    public Cook get_Cook(){ return this.complained_cook; }
    public String getDetails(){ return this.details; }
>>>>>>> e969f3f33f542ff857d69322cb88b700bdd2de3d

//there should be a list of complaints that the admin can view and it is added to the database
//with an add_complaints() method, each time a client makes a complaint, it's added here


}
