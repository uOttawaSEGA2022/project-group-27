package com.example.mealerapp;

public class User {

    private UserRole role;

    private String firstName, lastName, Address, Email, Password;

    public User (UserRole role, String firstName, String Address, String lastName, String Email, String Password){
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.Address = Address;
        this.Email = Email;
        this.Password = Password;

    }

    public User(){
    }

    public void setName(String firstName, String lastName){

        this.firstName = firstName;
        this.lastName = lastName;

    }

    public String getName(){
        return this.firstName + " " + this.lastName;
    }

    public void setAddress (String Address) {
        this.Address = Address;
    }

    public String getAddress(){
        return this.Address;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getEmail(){
        return this.Email;
    }

    public void setPassword(String Password){
        this.Password = Password;
    }

    public String getPassword(){
        return this.Password;
    }

    public void setRole(UserRole role){this.role=role;}

    public UserRole getRole(){return this.role;}





}
