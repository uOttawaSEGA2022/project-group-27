package com.example.mealerapp;

import java.util.ArrayList;

public class Cook extends User{

    private String description;
    private ArrayList<Meal> meals;

    public Cook(UserRole role, String firstName, String Address, String lastName, String Email, String Password, String desc){
        super(role, firstName, lastName, Address, Email, Password);
        description = desc;
    }

    public void addMeal(Meal m){
        meals.add(m);
    }


}
