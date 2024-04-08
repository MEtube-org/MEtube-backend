package com.metube.metubebackend.controllers;

import com.metube.metubebackend.entities.User;
import com.metube.metubebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/hello")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getUsers();
    }


}