package com.example.mealerapp.Objects;


public class Complaint {

    private String cookID;  //added by Carl because the activity is needed
    private String description;
    private String ID;
    private boolean actioned;

    public Complaint() {}

    public Complaint(String cookID, String description, String ID) {
        this.cookID = cookID;
        this.description = description;
        this.ID = ID;
        this.actioned = false;
    }

    public String getCookID() {
        return cookID;
    }

    public void setCookID(String cookID) {
        this.cookID = cookID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public boolean isActioned() {
        return actioned;
    }

    public void setActioned(boolean actioned) {
        this.actioned = actioned;
    }
}
