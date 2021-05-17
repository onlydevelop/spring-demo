package com.example.demo.controller;

import com.example.demo.Entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/users")
    public User getUser(@RequestParam(value = "name", defaultValue = "world") String name) {
        return new User(name, 10);
    }
}
