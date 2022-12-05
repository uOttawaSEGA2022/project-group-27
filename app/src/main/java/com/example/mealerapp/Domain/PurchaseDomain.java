package com.example.mealerapp.Domain;

import com.example.mealerapp.Objects.Purchase;

public class PurchaseDomain {

    public String purchase_meal_id;
    public String status;
    public String _id;

    public PurchaseDomain(String purchase_meal_id, String status, String id){
        this.purchase_meal_id = purchase_meal_id;
        this.status = status;
        this._id = id;
    }
    public PurchaseDomain(Purchase purchase){
        this.purchase_meal_id = purchase.getID();
        this.status = purchase.getStatus();
        this._id = purchase.getID();
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_id() {
        return _id;
    }

    public String getStatus() {
        return status;
    }

    public String getPurchase_meal_id() {
        return purchase_meal_id;
    }

    public void setPurchase_meal_id(String purchase_meal_id) {
        this.purchase_meal_id = purchase_meal_id;
    }
}
