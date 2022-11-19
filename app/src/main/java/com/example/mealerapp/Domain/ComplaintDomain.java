package com.example.mealerapp.Domain;

import com.example.mealerapp.Objects.Complaint;

import java.util.ArrayList;

public class ComplaintDomain {

    public String complained_cook_id;
    public String description;
    public String _id;
    public boolean isActioned = false;


    public ComplaintDomain(String target_cook_id, String details, String id) {
        this.complained_cook_id = target_cook_id;
        this.description = details;
        this._id = id;
    }
    public ComplaintDomain(Complaint complaint) {
        this.complained_cook_id = complaint.getComplained_cook_id();
        this.description = complaint.getDescription();
        this._id = complaint.get_id();
    }

    public boolean getActioned() { return this.getActioned(); }

    public String getId() { return this._id; }

    public String get_Cook_ID() { return this.complained_cook_id; }

    public String getDescription() { return this.description; }

    public void setId(String id) { this._id = id; }

    public void setActioned(boolean actioned) { this.isActioned = actioned; }

}
