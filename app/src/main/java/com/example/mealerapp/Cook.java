package com.example.mealerapp;

//import java.util.ArrayList;

public class Cook extends User{

    private String description;
    //private ArrayList<Meal> meals;

    public Cook(String firstName, String Address, String lastName, String Email, String Password, String desc){
        super(firstName, Address, lastName, Email, Password);
        description = desc;
    }
/*
    public void addMeal(Meal m){
        meals.add(m);
    }
*/

}
