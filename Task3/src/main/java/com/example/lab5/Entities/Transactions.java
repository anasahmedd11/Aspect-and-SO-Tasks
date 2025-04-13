package com.example.lab5.Entities;

import jakarta.persistence.*;

@Entity
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double amount;
    private String type;
    private String date;

    @ManyToOne
    @JoinColumn(name = "user_id")  // Foreign key in Transactions table
    private User user;

    public Transactions() {
    }

    public Transactions(User user, double amount, String type, String date) {
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
