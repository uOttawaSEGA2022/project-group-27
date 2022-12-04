package com.example.mealerapp.Objects;

import android.widget.ArrayAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Cook extends User implements Serializable {

    private String description;

    private int rating;

    private boolean suspended = false;
    private Date until = null;

    public Cook() {}

    public Cook(String Role, String firstName, String lastName, String Address, String Email, String Password, String desc, String UID){
        super(Role, firstName, lastName, Address, Email, Password, UID);
        this.description = desc;
        this.rating = 5;
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


    public boolean getSuspended(){return suspended;}

    public Date getUntil(){return until;}

    public void setSuspended(boolean s){suspended = s;}

    public void setUntil(Date d){until = d;}

    public String getDescription(){return description;}

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    //    public void addMeal(Meal m){
//        meals.add(m);
//    }
//
//    public ArrayList<Meal> get_mealList(){ return meals; }
//    public void setMeals(ArrayList<Meal> new_meals) { meals = new_meals; }


}
