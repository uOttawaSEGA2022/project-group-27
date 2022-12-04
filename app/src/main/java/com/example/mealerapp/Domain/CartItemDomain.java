package com.example.mealerapp.Domain;

public class CartItemDomain {

    private Double price;
    private int qty;
    private String mealName;
    private MealDomain mealDomain;

    public CartItemDomain(Double price, int qty, String mealName){
        this.price = price;
        this.qty = qty;
        this.mealName = mealName;
    }

    public CartItemDomain(MealDomain mealDomain){
        this.mealDomain = mealDomain;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public MealDomain getMealDomain() {
        return mealDomain;
    }

    public void setMealDomain(MealDomain mealDomain) {
        this.mealDomain = mealDomain;
    }
}
