package com.example.mealerapp.Objects;

import java.util.Date;

public class User {

    private String role;

    private String firstName, lastName, Address, Email, Password, uid;


    public User (String role, String firstName, String lastName, String Address, String Email, String Password, String uid){
        this.uid = uid;
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
    public String getFirstName() { return this.firstName; }

    public String getLastName() { return this.lastName;}

//    public String getName(){
//        return this.firstName + " " + this.lastName;
//    }

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

    public void setRole(String role){this.role=role;}

    public String getRole(){return this.role;}

    public String getuid() { return this.uid; }

    public String toString(){
        return "Name: " + firstName + " " + lastName + " UserID: " + uid + " Role: " + role;
    }

}
