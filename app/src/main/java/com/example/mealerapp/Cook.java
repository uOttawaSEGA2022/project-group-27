package com.example.mealerapp;

import java.util.ArrayList;
import java.util.Date;

public class Cook extends User{

    private String description;
    private ArrayList<Meal> meals;

    public Cook(String Role, String firstName, String lastName, String Address, String Email, String Password, String desc){
        super(Role, firstName, lastName, Address, Email, Password);
        description = desc;
    }

    public void addMeal(Meal m){
        meals.add(m);
    }


}
