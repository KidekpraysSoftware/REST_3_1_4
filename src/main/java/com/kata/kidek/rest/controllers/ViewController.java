package com.kata.kidek.rest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/user")
    public String getUserPage() {
        return "user";
    }

    @GetMapping("/admin")
    public String getAdminPage() {
        return "admin";
    }

    @GetMapping("/")
    public String getLoginPage() {
        return "redirect:/login";
    }
}
