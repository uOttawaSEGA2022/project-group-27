package com.example.mealerapp.Objects;

import java.security.PrivateKey;
import java.util.UUID;

public class Purchase {
    //this is the meals which offered by clients
    private String mealID;
    private String cookID;
    private String status;
    private String clientID;
    private String ID;
    private String mealName;

    public Purchase(){}

    public Purchase(String mealID, String cook_ID, String client_ID, String mealName){
        this.mealID = mealID;
        this.cookID = cook_ID;
        this.clientID = client_ID;
        this.status = "Pending";
        this.ID = UUID.randomUUID().toString();
        this.mealName = mealName;
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
