package com.pojos;

import java.util.Date;

public class Users {

    private String firstname;
    private String lastname;
    private String username;


    private String password;
    private String status = "User";
    private int user_id;
    private String email;



    // prompt user with options if they choose submit reimbursement then will have them fill out the info and place into the method
    public Users()
    {

    }
    public Users(String firstname, String lastname, String email, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.status = "User";

    }
    public Users(String firstname, String lastname,String email, String username, String password, String status ) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.status = status;
    }
    public Users(String firstname, String lastname, String email, String username, String password, String status, int id ) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.status = status;
        this.user_id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "ID: "+ getUser_id() + " First Name: " + getFirstname() + " Last Name: " + getLastname() + " User Name: " + getUsername() + " Password:" + getPassword() + " Status: " + getStatus();

    }
}

//this.lastname = lastname;
//        this.username = username;
//        this.password = password;
//        this.status = status;

