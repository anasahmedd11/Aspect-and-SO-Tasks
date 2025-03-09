package com.example.demo.controllers;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MoreComplexController {

    private Map<String, String> dataStore = new HashMap<>();

    @GetMapping("/hello")
    public Map<String, String> getMethod() {
        return Map.of("message", "Hello from GET method!", "status", "success");
    }

    //This method accepts a JSON body (@RequestBody Map<String, String> requestBody)
    @PostMapping("/hello")
    public Map<String, String> postMethod(@RequestBody Map<String, String> requestBody) {
        dataStore.put("message", requestBody.get("message"));
        return Map.of("message", "Hello from POST method! Data saved.", "status", "success");
    }

    //This method accepts a JSON body (@RequestBody Map<String, String> requestBody)
    @PutMapping("/hello")
    public Map<String, String> putMethod(@RequestBody Map<String, String> updatedMessage) {
        dataStore.put("message", updatedMessage.get("message"));
        return Map.of("message", "Hello from PUT method! Data updated.", "status", "success");
    }

    @DeleteMapping("/hello")
    public Map<String, String> deleteMethod() {
        dataStore.clear();
        return Map.of("message", "Hello from DELETE method! Data cleared.", "status", "success");
    }
}

//Including headers like Content-Type: application/json in POST and PUT requests is necessary
// because POST and PUT send data in the request body, and the server needs to understand what format the data is in.
//Content-Type: application/json tells the server that the request body contains JSON data.
