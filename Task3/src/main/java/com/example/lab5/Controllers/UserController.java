package com.example.lab5.Controllers;

import com.example.lab5.DTO.createUserDTO;
import com.example.lab5.DTO.updateUserDTO;
import com.example.lab5.Entities.User;
import com.example.lab5.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity <List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        //Sends HTTP 200 and includes the Student object in the body.
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        Optional<User> user = userService.findUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        //Sends HTTP 404 with no response body.
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/balance/{balance}")
    public ResponseEntity <List<User>> getUserByBalance(@PathVariable double balance){
        List<User> users = userService.findUserByBalance(balance);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody createUserDTO createUserDTO) {
        User newUser = userService.createUser(createUserDTO);
        //Return 200 with status CREATED instead of Ok
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @Valid @RequestBody updateUserDTO updateUserDTO){
        try{
            User updatedUser = userService.updateUser(id, updateUserDTO);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id){
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
