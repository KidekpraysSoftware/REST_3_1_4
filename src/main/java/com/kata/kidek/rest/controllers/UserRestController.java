package com.kata.kidek.rest.controllers;

import com.kata.kidek.rest.model.User;
import com.kata.kidek.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public User getCurrentUser(@CurrentSecurityContext(expression = "authentication?.name") String email) {
        return userService.getUserByEmail(email);
    }
}
