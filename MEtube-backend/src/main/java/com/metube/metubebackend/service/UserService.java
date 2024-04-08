package com.metube.metubebackend.service;

import com.metube.metubebackend.controllers.contracts.UserCreateRequest;
import com.metube.metubebackend.entities.User;
import com.metube.metubebackend.exceptions.EntityNotFoundException;
import com.metube.metubebackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User createUser(UserCreateRequest user){
        User newUser = user.toUser();
        userRepository.save(newUser);
        return newUser;
    }

    public User updateUserPassword(String id, String password){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())
            throw new EntityNotFoundException("User");
        User newUser = user.get();
        newUser.setPassword(password);
        return userRepository.save(newUser);
    }

    public void deleteUser(String id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())
            throw new EntityNotFoundException("User");
        userRepository.deleteById(id);
    }

}
