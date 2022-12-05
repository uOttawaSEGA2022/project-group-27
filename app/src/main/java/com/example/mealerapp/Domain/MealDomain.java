package com.example.mealerapp.Domain;

import com.example.mealerapp.Objects.Meal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MealDomain {

    private String name;
    private String description;
    private ArrayList<String> ingredients;
    private ArrayList<String> allergens;
    private String cookID;
    private String ID;
    private int rating;
    private String icon;
    private Double price;
    private String course;
    private String cuisine;



    public MealDomain(Meal meal){
        this.name = meal.getName();
        this.description = meal.getDescription();
        this.ingredients = meal.getIngredients();
        this.allergens = meal.getAllergens();
        this.ID = meal.getID();
        this.rating = meal.getRating();
        this.price = meal.getPrice();
        this.course = meal.getCourse();
        this.cuisine = meal.getCuisine();
        this.cookID = meal.getCookID();
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Map<String, String> toHashMap(){

        Map<String, String> data = new HashMap<>();
        data.put("name", name);
        data.put("course", course);
        data.put("cuisine", cuisine);
        data.put("ingredients", String.join(",", ingredients));
        data.put("allergens", String.join(",", allergens));
        data.put("price", "" + price);
        data.put("description", description);
        data.put("rating", ""+rating);
        data.put("ID", ID);
        data.put("cookID", cookID);

        return data;
    }

}
