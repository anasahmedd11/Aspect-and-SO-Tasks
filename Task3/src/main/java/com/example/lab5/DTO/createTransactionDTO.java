package com.example.lab5.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class createTransactionDTO {

    @NotNull(message = "Amount can not be null")
    private double amount;

    @NotBlank(message = "Type can not be empty")
    private String type;

    @NotBlank(message = "Date can not be empty")
    private String date;

    private Long userId;

    public createTransactionDTO() {
    }

    public createTransactionDTO(double amount, String type, String date) {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
