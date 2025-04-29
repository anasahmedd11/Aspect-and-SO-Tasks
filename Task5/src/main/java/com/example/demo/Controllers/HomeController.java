package com.example.demo.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class HomeController {

    @GetMapping("/user")
    @PreAuthorize("isAuthenticated()") // Only checks authentication, not roles
    public String userAccess() {
        return "Hello from the protected User Content.";
    }

    @GetMapping("/admin")
    @PreAuthorize("isAuthenticated()")
    public String adminAccess() {
        return "Hello from the protected Admin Content.";
    }

    //No authentication needed
    @GetMapping("/all")
    public String allAccess() {
        return "Hello from the unprotected Public Content.";
    }

} 