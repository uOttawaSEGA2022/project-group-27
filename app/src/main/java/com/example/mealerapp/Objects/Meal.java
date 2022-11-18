package com.example.mealerapp.Objects;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.UUID;

public class Meal {

    private String name;
    private String course;
    private String cuisine;
    private ArrayList<String> ingredients;
    private ArrayList<String> allergens;
    private Double price;
    private String description;
    private String ID;
    private boolean offered;


    public Meal(){}

    public Meal(String name, String course, String cuisine, ArrayList<String> ingredients, ArrayList<String> allergens, Double price, String description) {
        this.name = name;
        this.course = course;
        this.cuisine = cuisine;
        this.ingredients = ingredients;
        this.allergens = allergens;
        this.price = price;
        this.description = description;
        this.ID = UUID.randomUUID().toString();
        this.offered = false;
    }
    public Meal(String name, String course, String cuisine, ArrayList<String> ingredients, ArrayList<String> allergens, Double price, String description, String ID) {
        this.name = name;
        this.course = course;
        this.cuisine = cuisine;
        this.ingredients = ingredients;
        this.allergens = allergens;
        this.price = price;
        this.description = description;
        this.offered = false;
        this.ID = ID;
    }

    public boolean getOffered(){ return this.offered; }
    public void setOffered(boolean offered) { this.offered = offered; }

    public String getID(){
        return ID;
    }
    public String getName() { return name; }
    public String getCourse() { return course; }
    public String getCuisine() { return cuisine; }
    public ArrayList<String> getIngredients() { return ingredients; }
    public ArrayList<String> getAllergens() { return allergens; }
    public Double getPrice() { return price; }
    public String getDescription() { return description; }

}
