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
    private String cookID;
    private boolean offered;
    private int rating;

    public Meal(){}

    public Meal(String name, String course, String cuisine, ArrayList<String> ingredients, ArrayList<String> allergens, Double price, String description, String cookID) {
        this.name = name;
        this.course = course;
        this.cuisine = cuisine;
        this.ingredients = ingredients;
        this.allergens = allergens;
        this.price = price;
        this.description = description;
        this.ID = UUID.randomUUID().toString();
        this.offered = false;
        this.cookID = cookID;
    }
    public Meal(String name, String course, String cuisine, ArrayList<String> ingredients, ArrayList<String> allergens, Double price, String description, String cookID, String ID) {
        this.name = name;
        this.course = course;
        this.cuisine = cuisine;
        this.ingredients = ingredients;
        this.allergens = allergens;
        this.price = price;
        this.description = description;
        this.offered = false;
        this.ID = ID;
        this.cookID = cookID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getAllergens() {
        return allergens;
    }

    public void setAllergens(ArrayList<String> allergens) {
        this.allergens = allergens;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public String getCookID() {
        return cookID;
    }

    public void setCookID(String cookID) {
        this.cookID = cookID;
    }

    public boolean isOffered() {
        return offered;
    }

    public void setOffered(boolean offered) {
        this.offered = offered;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
