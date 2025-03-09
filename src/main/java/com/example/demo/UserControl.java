package com.example.demo;
import com.example.demo.DTO.createUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/task2/users")
public class UserControl {

    @Autowired
    private final UserService userService;

    public UserControl(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        return this.userService.getUsersFromService();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Integer id) {
        return this.userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody createUserDTO createUserDTO){
        return this.userService.createUser(createUserDTO);
    }


    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody createUserDTO userDTO) {
        return this.userService.updateUser(id, userDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {
        return this.userService.deleteUser(id);
    }

}
