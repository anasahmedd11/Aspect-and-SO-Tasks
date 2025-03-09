package com.example.demo;
import com.example.demo.DTO.createUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsersFromService(){
        return this.userRepository.findAll();
    }

    public User createUser(createUserDTO userDTO){
        User user = new User(
                userDTO.getEmail(),
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getPhoneNumber()
        );
        return this.userRepository.save(user);
    }

    public Optional<User> getUserById(Integer id) {
        return this.userRepository.findById(id);
    }

//    public User updateUser(Integer id, createUserDTO userDTO) {
//        Optional<User> us = userRepository.findById(id);
//
//        if (us.isPresent()) {
//            User user = us.get();
//            user.setEmail(userDTO.getEmail());
//            user.setUsername(userDTO.getUsername());
//            user.setPassword(userDTO.getPassword());
//            user.setPhoneNumber(userDTO.getPhoneNumber());
//
//            User updatedUser = userRepository.save(user);
//            System.out.println("User with ID " + id + " updated successfully!");
//            return updatedUser;
//        } else {
//            throw new RuntimeException("User not found with ID: " + id);
//        }
//    }

    // Cleaner approach for update:
    public User updateUser(Integer id, createUserDTO userDTO) {
        return userRepository.findById(id).map(user -> {
            user.setEmail(userDTO.getEmail());
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            System.out.println("User with ID " + id + " updated successfully!");
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }


    public String deleteUser(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User with ID " + id + " has been deleted.";
        } else {
            throw new RuntimeException("User not found with ID: " + id);
        }
    }
}