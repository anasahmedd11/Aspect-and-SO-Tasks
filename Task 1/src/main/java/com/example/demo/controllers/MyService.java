package com.example.demo.controllers;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    public void doSomething(String requestType) {
        System.out.println("MyService: Processing " + requestType + " request");
    }
}
