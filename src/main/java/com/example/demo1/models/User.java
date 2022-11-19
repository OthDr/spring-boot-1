package com.example.demo1.models;


import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.ModelAttribute;

public class User {

    private int id;
    private String firstname;
    private String lastname;

    public User() {
    }



    public String getFirstname() {
        return firstname;
    }

    public User(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public User(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
