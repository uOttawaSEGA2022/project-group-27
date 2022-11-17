package com.example.mealerapp.Objects;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.security.PublicKey;
import java.util.ArrayList;

public class Meal {

    protected String name;
    protected String course;
    protected String cuisine;
    protected ArrayList<String> ingredients;
    protected ArrayList<String> allergens;
    protected Double price;
    protected String description;

    public Meal(){}

    public Meal(String name, String course, String cuisine, ArrayList<String> ingredients, ArrayList<String> allergens, Double price, String description) {
        this.name = name;
        this.course = course;
        this.cuisine = cuisine;
        this.ingredients = ingredients;
        this.allergens = allergens;
        this.price = price;
        this.description = description;
    }
    public String getName() { return name; }
    public String getCourse() { return course; }
    public String getCuisine() { return cuisine; }
    public ArrayList<String> getIngredients() { return ingredients; }
    public ArrayList<String> getAllergens() { return allergens; }
    public BigDecimal getPrice() { return price; }
    public String getDescription() { return description; }

}
