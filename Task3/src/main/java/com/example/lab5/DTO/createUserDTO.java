package com.example.lab5.DTO;

import jakarta.validation.constraints.*;

public class createUserDTO {

    @NotBlank(message = "Name can not be empty")
    private String name;

    @Pattern(regexp = "^\\+20(10|11|12|15)[0-9]{8}$", message = "Invalid Egyptian phone number")
    @NotBlank(message = "Phone Number can not be empty")
    private String phoneNumber;

    @Email(message = "Email format is invalid")
    @NotBlank(message = "Email can not be empty")
    private String email;

    @Positive(message = "Balance can not be negative")
    @NotNull(message = "Balance can not be empty")
    private double balance;

    public createUserDTO() {}

    public createUserDTO(String name, String phoneNumber, String email, double balance) {}

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

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
