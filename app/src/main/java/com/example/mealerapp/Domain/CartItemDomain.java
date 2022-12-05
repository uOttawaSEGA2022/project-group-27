package com.example.mealerapp.Domain;

import com.example.mealerapp.Objects.CartItem;

public class CartItemDomain {

    private Double price;
    private int qty;
    private String mealName;
    private Double subtotal;
    private String mealID;
    private String ID;

    public CartItemDomain(Double price, int qty, String mealName, Double subtotal, String mealID, String ID) {
        this.price = price;
        this.qty = qty;
        this.mealName = mealName;
        this.subtotal = subtotal;
        this.mealID = mealID;
        this.ID = ID;
    }

    public CartItemDomain(CartItem item){
        this.price = item.getPrice();
        this.qty = item.getQty();
        this.mealName = item.getMealName();
        this.subtotal = item.getSubtotal();
        this.mealID = item.getMealID();
        this.ID = item.getID();
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

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public String getMealID() {
        return mealID;
    }

    public void setMealID(String mealID) {
        this.mealID = mealID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
