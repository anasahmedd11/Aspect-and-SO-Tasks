package com.example.lab5.Services;

import com.example.lab5.DTO.createUserDTO;
import com.example.lab5.DTO.updateUserDTO;
import com.example.lab5.Entities.User;
import com.example.lab5.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findUserByBalance(double balance) {
        return userRepository.findByBalance(balance);
    }

    public User createUser(createUserDTO createUserDTO){
        User user = new User(
                createUserDTO.getName(),
                createUserDTO.getEmail(),
                createUserDTO.getPhoneNumber(),
                createUserDTO.getBalance()
        );
        return userRepository.save(user);
    }

    public User updateUser(Long id, updateUserDTO updateUserDTO){
        return userRepository.findById(id).map(user -> {
            user.setName(updateUserDTO.getName());
            user.setEmail(updateUserDTO.getEmail());
            user.setPhoneNumber(updateUserDTO.getPhoneNumber());
            user.setBalance(updateUserDTO.getBalance());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        userRepository.deleteById(id);
    }

}
