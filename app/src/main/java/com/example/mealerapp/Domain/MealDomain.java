package com.example.mealerapp.Domain;

import com.example.mealerapp.Objects.Meal;

import java.util.ArrayList;

public class MealDomain {

    private String name;
    private String description;
    private ArrayList<String> ingredients;
    private ArrayList<String> allergens;
    private String cookID;
    private String ID;
    private int rating;
    private String icon;

    public MealDomain(Meal meal){
        this.name = meal.getName();
        this.description = meal.getDescription();
        this.ingredients = meal.getIngredients();
        this.allergens = meal.getAllergens();
        this.ID = meal.getID();
        this.rating = meal.getRating();
    }

    public MealDomain(String name, String description, ArrayList<String> ingredients, ArrayList<String> allergens, String ID){
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.allergens = allergens;
        this.ID = ID;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
