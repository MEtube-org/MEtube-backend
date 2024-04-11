package com.metube.metubebackend.service;

import com.metube.metubebackend.controllers.contracts.UserCreateRequest;
import com.metube.metubebackend.entities.UserEntity;
import com.metube.metubebackend.exceptions.EntityNotFoundException;
import com.metube.metubebackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }

    public UserEntity createUser(UserCreateRequest user){
        UserEntity newUserEntity = user.toUser();
        newUserEntity.setPassword(passwordEncoder.encode(newUserEntity.getPassword()));
        userRepository.save(newUserEntity);
        return newUserEntity;
    }

    public UserEntity updateUserPassword(String id, String password){
        Optional<UserEntity> user = userRepository.findById(id);
        if(user.isEmpty())
            throw new EntityNotFoundException("User");
        UserEntity newUserEntity = user.get();
        newUserEntity.setPassword(password);
        return userRepository.save(newUserEntity);
    }

    public void deleteUser(String id){
        Optional<UserEntity> user = userRepository.findById(id);
        if(user.isEmpty())
            throw new EntityNotFoundException("User");
        userRepository.deleteById(id);
    }

}
