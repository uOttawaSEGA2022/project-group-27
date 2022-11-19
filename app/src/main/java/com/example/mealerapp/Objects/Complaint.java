package com.example.mealerapp.Objects;


public class Complaint {

    public String complained_cook_id;  //added by Carl because the activity is needed
    public String description;
    public String _id;
    public boolean isActioned = false;




    public Complaint(){}

    public Complaint(String target_cook_id, String details, String id) {
        this.complained_cook_id = target_cook_id;
        this.description = details;
        this._id = id;
    }

    public String getComplained_cook_id() {
        return complained_cook_id;
    }

    public void setComplained_cook_id(String complained_cook_id) {
        this.complained_cook_id = complained_cook_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public boolean isActioned() {
        return isActioned;
    }

    public void setActioned(boolean actioned) {
        isActioned = actioned;
    }


//there should be a list of complaints that the admin can view and it is added to the database
//with an add_complaints() method, each time a client makes a complaint, it's added here


}
