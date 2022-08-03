package com.kata.kidek.rest.controllers;


import com.kata.kidek.rest.model.User;
import com.kata.kidek.rest.service.RoleService;
import com.kata.kidek.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


//    @GetMapping()
//    public User userPage(@CurrentSecurityContext(expression = "authentication?.name") String email) {
//        return userService.getUserByEmail(email);
//    }

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

        System.out.println(user);
        userService.edit(user);
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }




//    @GetMapping()
//    public String adminPage(@CurrentSecurityContext(expression = "authentication?.name") String mail, Model model) {
//        User admin = userService.getUserByMail(mail);
//        model.addAttribute("admin", admin);
//        model.addAttribute("newUser", new User());
//        model.addAttribute("userList", userService.getAllUsers());
//        model.addAttribute("roleList", roleService.getAllRoles());
//        return "admin";
//    }
//
//    @PostMapping("/new")
//    public String saveUser(@ModelAttribute("newUser") User user) {
//        userService.saveUser(user);
//        return "redirect:/admin";
//    }
//
//    @PatchMapping("/{id}")
//    public String update(@ModelAttribute("editUser") User user) {
//        userService.edit(user);
//        return "redirect:/admin";
//    }
//
//    @DeleteMapping("/{id}/delete")
//    public String deleteUser(@PathVariable("id") Long id) {
//        userService.deleteUserById(id);
//        return "redirect:/admin";
//    }
}
