package com.example.mealerapp.Objects;

import com.example.mealerapp.Domain.MealDomain;

public class CartItem {

    private Double price;
    private int qty;
    private String mealName;
    private Double subtotal;
    private String ID;
    private String mealID;
    private String cookID;
    private String clientID;

    public CartItem(Double price, int qty, String mealName, Double subtotal, String ID, String mealID, String cookID, String clientID) {
        this.price = price;
        this.qty = qty;
        this.mealName = mealName;
        this.subtotal = subtotal;
        this.ID = ID;
        this.mealID = mealID;
        this.cookID = cookID;
        this.clientID = clientID;
    }

    public CartItem() {}

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

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMealID() {
        return mealID;
    }

    public void setMealID(String mealID) {
        this.mealID = mealID;
    }

    public String getCookID() {
        return cookID;
    }

    public void setCookID(String cookID) {
        this.cookID = cookID;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }
}
