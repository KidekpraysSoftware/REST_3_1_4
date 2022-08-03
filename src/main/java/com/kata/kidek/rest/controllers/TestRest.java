package com.kata.kidek.rest.controllers;

import com.kata.kidek.rest.model.User;
import com.kata.kidek.rest.service.RoleService;
import com.kata.kidek.rest.service.UserService;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/test")
public class TestRest {

    private final UserService userService;
    private final RoleService roleService;


    public TestRest(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/2")
    public User userPage(@CurrentSecurityContext(expression = "authentication?.name") String email) {
        System.out.println("get request!!!");
        return userService.getUserByEmail(email);
    }
}
