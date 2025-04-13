package com.example.lab5.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class updateUserDTO {

    private String name;

    @Pattern(regexp = "^\\+20(10|11|12|15)[0-9]{8}$", message = "Invalid Egyptian phone number")
    private String phoneNumber;

    @Email(message = "Email format is invalid")
    private String email;

    @Positive(message = "Balance can not be negative")
    private double balance;

    public updateUserDTO() {}

    public updateUserDTO(String name, String phoneNumber, String email, double balance) {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email) {
        this.email = email;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
