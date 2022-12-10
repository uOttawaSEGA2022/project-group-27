package com.example.mealerapp.Objects;

import android.widget.ArrayAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Cook extends User implements Serializable {

    private String description;

    private double rating;

    private ArrayList<Double> ratings;

    private boolean suspended = false;
    private Date until = null;

    public Cook() {}

    public Cook(String Role, String firstName, String lastName, String Address, String Email, String Password, String desc, String UID, double rating, ArrayList<Double> ratings){
        super(Role, firstName, lastName, Address, Email, Password, UID);
        this.description = desc;
        this.rating = rating;
        this.ratings = ratings;
    }

    public Cook(String Role, String firstName, String lastName, String Address, String Email, String Password, String desc, String UID, int rating){
        super(Role, firstName, lastName, Address, Email, Password, UID);
        this.description = desc;
        this.rating = rating;
    }

    public Cook(User user, String desc){
        super(user.getRole(), user.getFirstName(), user.getLastName(), user.getAddress(), user.getEmail(), user.getPassword(), user.getuid());
        this.description = desc;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public ArrayList<Double> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<Double> ratings) {
        this.ratings = ratings;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public Date getUntil() {
        return until;
    }

    public void setUntil(Date until) {
        this.until = until;
    }
}
