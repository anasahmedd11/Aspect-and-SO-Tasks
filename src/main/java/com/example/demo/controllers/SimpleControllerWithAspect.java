package com.example.demo.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class SimpleControllerWithAspect {

    private final MyService myService;

    public SimpleControllerWithAspect(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/get")
    public String getData() {
        myService.doSomething("GET");
        return "GET request received: Fetching data";
    }

    //If POST or PUT request sends plain text, no need to explicitly specify headers in Postman
    //as it will set text/plain automatically

    @PostMapping("/post")
    public String postData(@RequestBody String data) {
        myService.doSomething("POST");
        return "POST request received: Data saved - " + data;
    }

    @PutMapping("/put")
    public String putData(@RequestBody String data) {
        myService.doSomething("PUT");
        return "PUT request received: Data updated - " + data;
    }

    @DeleteMapping("/delete")
    public String deleteData() {
        myService.doSomething("DELETE");
        return "DELETE request received: Data deleted";
    }
}

