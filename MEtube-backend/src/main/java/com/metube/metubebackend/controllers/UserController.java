package com.metube.metubebackend.controllers;

import com.metube.metubebackend.controllers.contracts.UserCreateRequest;
import com.metube.metubebackend.entities.User;
import com.metube.metubebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getUsers();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody UserCreateRequest user){
        return userService.createUser(user);
    }

    @PutMapping("/users/{id}")
    public User updateUserPassword(@PathVariable String id, @RequestBody String password){
        return userService.updateUserPassword(id, password);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable String id){
        userService.deleteUser(id);
    }



}