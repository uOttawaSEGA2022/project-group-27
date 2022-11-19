package com.example.mealerapp.Domain;

import com.example.mealerapp.Objects.Meal;

import java.util.ArrayList;

public class MealDomain {

    private String name;
    private String description;
    private ArrayList<String> ingredients;
    private ArrayList<String> allergens;
    private String ID;
    private boolean offered;

    public MealDomain(String name, String description, ArrayList<String> ingredients, ArrayList<String> allergens, boolean offered, String ID){

        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.allergens = allergens;
        this.offered = offered;
        this.ID = ID;

    }

    public MealDomain(Meal meal){
        this.name = meal.getName();
        this.description = meal.getDescription();
        this.ingredients = meal.getIngredients();
        this.allergens = meal.getAllergens();
        this.offered = meal.isOffered();
        this.ID = meal.getID();
    }


    public boolean getOffered(){ return this.offered; }

    public void setOffered(boolean offered) { this.offered = offered; }

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

    public boolean isOffered() {
        return offered;
    }
}