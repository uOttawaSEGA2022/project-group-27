package com.example.mealerapp;

public class Address {


    //TODO Use Google Map Places API to auto fill address field and create address object (This task is quite difficult and extra so Mohamed will do it)
    private String street, provOrState, city, country, postalCode;

    public Address(String street, String city, String provOrState, String country, String postalCode){
        this.street = street;
        this.city = city;
        this.provOrState=provOrState;
        this.country=country;
        this.postalCode=postalCode;
    }

    public String getStreet(){return this.street;}

    public String getCity(){return this.city;}

    public String getProvOrState(){return this.provOrState;}

    public String getCountry(){return this.country;}

    public String getPostalCode(){return this.postalCode;}

    //there will be no need for setters in Address class since changing an Address will require all components to be changed
}
