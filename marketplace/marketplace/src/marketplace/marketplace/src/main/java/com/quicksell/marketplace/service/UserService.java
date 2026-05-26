package com.quicksell.marketplace.service;

import com.quicksell.marketplace.entity.User;
import com.quicksell.marketplace.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User registerUser(User user) {

        // check email already exists
        if(userRepository.findByEmail(user.getEmail()) != null) {

            throw new RuntimeException("Email already exists");
        }

        // encrypt password
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return userRepository.save(user);
    }

    public String loginUser(String email, String password) {

        User user = userRepository.findByEmail(email);

        if(user == null) {

            return "User not found";
        }

        boolean matched = passwordEncoder.matches(
                password,
                user.getPassword()
        );

        if(matched) {

            return "Login Successful";
        }

        return "Invalid Password";
    }
}
