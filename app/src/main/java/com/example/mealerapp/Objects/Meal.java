package com.example.mealerapp.Objects;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Meal {

    protected String name;
    protected String course;
    protected String cuisine;
    protected ArrayList<String> ingredients;
    protected ArrayList<String> allergens;
    protected BigDecimal price;
    protected String description;

    public Meal(String name, String course, String cuisine, ArrayList<String> ingredients, ArrayList<String> allergens, BigDecimal price, String description) {
        this.name = name;
        this.course = course;
        this.cuisine = cuisine;
        this.ingredients = ingredients;
        this.allergens = allergens;
        this.price = price;
        this.description = description;
    }

}
