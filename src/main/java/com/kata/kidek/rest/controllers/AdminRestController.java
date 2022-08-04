package com.kata.kidek.rest.controllers;

import com.kata.kidek.rest.model.User;
import com.kata.kidek.rest.service.RoleService;
import com.kata.kidek.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    private final UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping()
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PatchMapping()
    public void updateUser(@RequestBody User user) {
        userService.edit(user);
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }



}
