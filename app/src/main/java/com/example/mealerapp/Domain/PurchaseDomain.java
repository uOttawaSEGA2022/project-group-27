package com.example.mealerapp.Domain;

import com.example.mealerapp.Objects.Purchase;

public class PurchaseDomain extends NotificationDomain {

    private String mealID;
    private String cookID;
    private String status;
    private String clientID;
    private String ID;
    private String mealName;



    public PurchaseDomain(Purchase purchase){
        this.mealID = purchase.getMealID();

        this.status = purchase.getStatus();

        this.ID= purchase.getID();

        this.clientID = purchase.getClientID();
        this.cookID = purchase.getCookID();

        this.mealName = purchase.getMealName();



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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }
}
