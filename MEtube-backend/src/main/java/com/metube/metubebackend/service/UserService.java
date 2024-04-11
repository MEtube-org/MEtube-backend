package com.metube.metubebackend.service;

import com.metube.metubebackend.controllers.contracts.UserCreateRequest;
import com.metube.metubebackend.controllers.contracts.UserPasswordResetRequest;
import com.metube.metubebackend.entities.UserEntity;
import com.metube.metubebackend.exceptions.EntityAlreadyExistsException;
import com.metube.metubebackend.exceptions.EntityNotFoundException;
import com.metube.metubebackend.exceptions.ForbiddenException;
import com.metube.metubebackend.repositories.UserRepository;
import org.apache.catalina.User;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Objects;
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

    public String getUserId() throws BadRequestException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof UserDetails userDetails) {
            Optional<UserEntity> user = userRepository.findByUsername(userDetails.getUsername());
            if(user.isEmpty())
                throw new EntityNotFoundException("User");
            return user.get().getId();
        }
        else
            throw new BadRequestException();

    }

    public UserEntity createUser(UserCreateRequest user){
        if(userRepository.findByUsername(user.getUsername()).isPresent())
            throw new EntityAlreadyExistsException("User");
        UserEntity newUserEntity = user.toUser();
        newUserEntity.setPassword(passwordEncoder.encode(newUserEntity.getPassword()));
        userRepository.save(newUserEntity);
        return newUserEntity;
    }

    public UserEntity updateUserPassword(String id, UserPasswordResetRequest user) throws BadRequestException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<UserEntity> toUpdate = userRepository.findById(id);

        // User with the given id doesn't exist
        if(toUpdate.isEmpty())
            throw new EntityNotFoundException("User");

        // Can't update another user's password
        if (auth != null && auth.getPrincipal() instanceof UserDetails userDetails) {
            if(!Objects.equals(userDetails.getUsername(), toUpdate.get().getUsername()))
                throw new ForbiddenException("User");

            UserEntity newUserEntity = toUpdate.get();
            newUserEntity.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(newUserEntity);
        }
        else
            throw new BadRequestException();
    }

    public void deleteUser(String id){
        Optional<UserEntity> user = userRepository.findById(id);
        if(user.isEmpty())
            throw new EntityNotFoundException("User");
        userRepository.deleteById(id);
    }

}
