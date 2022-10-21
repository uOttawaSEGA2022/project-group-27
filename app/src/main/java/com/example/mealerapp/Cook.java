package com.example.mealerapp;

import java.util.ArrayList;

public class Cook extends User{

    private String description;
    private ArrayList<Meal> meals;

    public Cook(String name, String Address, String Email, String Password, String desc){
        super(name, Address, Email, Password);
        description = desc;
    }

    public void addMeal(Meal m){
        meals.add(m);
    }


}
