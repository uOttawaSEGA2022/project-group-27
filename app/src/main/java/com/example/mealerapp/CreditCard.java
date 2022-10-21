package com.example.mealerapp;


import java.util.Date;

public class CreditCard {

    private String nameOnCard;
    private String expirationDate;
    private long cardNumber;
    private int CVV;

    public CreditCard(){}
    public CreditCard(String nameOnCard, long cardNumber, String expirationDate, int CVV){
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cardNumber = cardNumber;
        this.CVV = CVV;

    }

    /* No need for setters since credit card information should not be able to be changed once made, a new credit card will need to be instantiated
    */


    public String getNameOnCard() {
        return this.nameOnCard;
    }

    public String getExpirationDate(){
        return this.expirationDate;
    }

    public long getCardNumber(){
        return this.cardNumber;
    }

    public int getCVV(){
        return this.CVV;
    }

}
