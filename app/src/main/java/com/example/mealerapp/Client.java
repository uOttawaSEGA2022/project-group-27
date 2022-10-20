package com.example.mealerapp;

import java.util.ArrayList;

public class Client extends User{


    private ArrayList<CreditCard> CreditCards;

    public Client (UserRole role, String firstName, String lastName, String Address, String Email, String Password, CreditCard CC){
        super(role, firstName, lastName, Address, Email, Password);
        this.CreditCards.add(CC);
    }

    public void addCreditCard(CreditCard CC){
        CreditCards.add(CC);
    }
}
