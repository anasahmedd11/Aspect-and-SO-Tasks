package com.example.lab5.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class updateTransactionDTO {

    private double amount;

    private String type;

    private String date;

    public updateTransactionDTO() {
    }

    public updateTransactionDTO(double amount, String type, String date) {
        this.amount = amount;
        this.type = type;
        this.date = date;
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
}
