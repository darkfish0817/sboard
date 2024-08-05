package com.indielab.demo.sboard.controller;

import com.indielab.demo.sboard.model.User;
import com.indielab.demo.sboard.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final UserService userService;

    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "account/login";
    }

    @GetMapping("/register")
    public String register() {
        return "account/register";
    }

    @PostMapping("/register")
    public String register(User user) {
        userService.save(user);
        return "redirect:/";
    }
}
