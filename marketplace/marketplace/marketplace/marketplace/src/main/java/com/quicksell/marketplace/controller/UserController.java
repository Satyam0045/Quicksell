package com.quicksell.marketplace.controller;

import com.quicksell.marketplace.dto.LoginRequest;
import com.quicksell.marketplace.entity.User;
import com.quicksell.marketplace.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {

        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public String loginUser(
            @RequestBody LoginRequest loginRequest
    ) {

        return userService.loginUser(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
    }
}
