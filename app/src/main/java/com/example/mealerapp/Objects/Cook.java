package com.example.mealerapp.Objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Cook extends User implements Serializable {

    private String description;
    private ArrayList<Meal> meals;

    public Cook() {}

    public Cook(String Role, String firstName, String lastName, String Address, String Email, String Password, String desc, String UID){
        super(Role, firstName, lastName, Address, Email, Password, UID);
        this.description = desc;
    }

    public Cook(User user, String desc){
        super(user.getRole(), user.getFirstName(), user.getLastName(), user.getAddress(), user.getEmail(), user.getPassword(), user.getUID());
        this.description = desc;
    }

    public void addMeal(Meal m){
        meals.add(m);
    }


}
