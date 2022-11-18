package com.example.mealerapp.Domain;

import java.util.ArrayList;

public class MealDomain {

    private String name;
    private String description;
    private ArrayList<String> ingredients;
    private ArrayList<String> allergens;

    public MealDomain(String name, String description, ArrayList<String> ingredients, ArrayList<String> allergens){

        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.allergens = allergens;

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
}
