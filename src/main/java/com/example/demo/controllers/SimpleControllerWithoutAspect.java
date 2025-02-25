package com.example.demo.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class SimpleControllerWithoutAspect {

    @GetMapping("/get_simple")
    public String getData() {
        return "GET request received: Fetching data";
    }

    @PostMapping("/post_simple")
    public String postData(@RequestBody String data) {
        return "POST request received: Data saved - " + data;
    }

    @PutMapping("/put_simple")
    public String putData(@RequestBody String data) {
        return "PUT request received: Data updated - " + data;
    }

    @DeleteMapping("/delete_simple")
    public String deleteData() {
        return "DELETE request received: Data deleted";
    }
}
