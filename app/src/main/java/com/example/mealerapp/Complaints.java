package com.example.mealerapp;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Complaints {

    public String complained_cook_id;  //added by Carl because the activity is needed
    public String description;
    public String _id;





    public Complaints(){}

    public Complaints(String target_cook_id, String details, String id) {
        this.complained_cook_id = target_cook_id;
        this.description = details;
        this._id = id;
    }



    public String get_Cook_ID(){ return complained_cook_id; }
    public String getDescription(){ return description; }
    public void setId(String id) {
        _id = id;
    }
    public String getId() {
        return _id;
    }

//    public String getName() { return complained_cook.getFirstName() + complained_cook.getLastName(); }




//there should be a list of complaints that the admin can view and it is added to the database
//with an add_complaints() method, each time a client makes a complaint, it's added here


}
