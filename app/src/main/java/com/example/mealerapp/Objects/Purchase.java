package com.example.mealerapp.Objects;

import java.security.PrivateKey;
import java.util.UUID;

public class Purchase {
    //this is the meals which offered by clients
    private String mealID;
    private String cook_ID;
    private String status;
    private String client_ID;
    private String ID;

    public Purchase(){}

    public Purchase(String mealID, String cook_ID, String client_ID){
        this.mealID = mealID;
        this.cook_ID = cook_ID;
        this.client_ID = client_ID;
        this.status = "Pending";
        this.ID = UUID.randomUUID().toString();
    }

    public Purchase(String mealID, String cook_ID, String client_ID, String ID){
        this.mealID = mealID;
        this.client_ID = client_ID;
        this.cook_ID = cook_ID;
        this.status = "Pending";
        this.ID = ID;
    }


    public String getMealID() {
        return mealID;
    }

    public void setMealID(String mealID) {
        this.mealID = mealID;
    }

    public String getCook_ID() {
        return cook_ID;
    }

    public void setCook_ID(String cook_ID) {
        this.cook_ID = cook_ID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClient_ID() {
        return client_ID;
    }

    public void setClient_ID(String client_ID) {
        this.client_ID = client_ID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
