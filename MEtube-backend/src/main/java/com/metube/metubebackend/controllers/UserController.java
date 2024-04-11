package com.metube.metubebackend.controllers;

import com.metube.metubebackend.controllers.contracts.UserCreateRequest;
import com.metube.metubebackend.entities.UserEntity;
import com.metube.metubebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping("/users")
    public List<UserEntity> getAllUsers(){
        return userService.getUsers();
    }

    @PostMapping("/register/user")
    public UserEntity createUser(@RequestBody UserCreateRequest user){
        return userService.createUser(user);
    }

    @PutMapping("/user/{id}")
    public UserEntity updateUserPassword(@PathVariable String id, @RequestBody String password){
        return userService.updateUserPassword(id, password);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable String id){
        userService.deleteUser(id);
    }



}