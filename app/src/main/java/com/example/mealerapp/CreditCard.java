package com.example.mealerapp;


import java.util.Date;

public class CreditCard {

    private String nameOnCard;
    private Date expirationDate;
    private long cardNumber;
    private int CVV;

    public CreditCard(String nameOnCard, Date expirationDate, long cardNumber, int CVV){
        this.cardNumber = cardNumber;
    }
    /* No need for setters since credit card information should not be able to be changed once made, a new credit card will need to be instantiated
    * For now getters are protected, may be changed to public if required later on
    */
    protected String getNameOnCard() {
        return this.nameOnCard;
    }

    protected Date getExpirationDate(){
        return this.expirationDate;
    }

    protected long getCardNumber(){
        return this.cardNumber;
    }

    protected int getCVV(){
        return this.CVV;
    }

}
