package com.example.lab5.Entities;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "app_user") //to escape the reserved user keyword
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String phoneNumber;
    private String email;
    private double balance;

    //orphanRemoval = true ensures that child records are only tied to the parent, and automatically cleaned up if removed from the parent's side.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transactions> transactions = new ArrayList<>();

    public User(String name, String phoneNumber, String email, double balance) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.balance = balance;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
