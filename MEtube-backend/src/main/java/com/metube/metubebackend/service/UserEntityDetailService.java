package com.metube.metubebackend.service;

import com.metube.metubebackend.entities.UserEntity;
import com.metube.metubebackend.exceptions.EntityNotFoundException;
import com.metube.metubebackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserEntityDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if(user.isEmpty())
            throw new UsernameNotFoundException(username);
        UserEntity userEntity = user.get();
        return User.builder()
                .username(username)
                .password(userEntity.getPassword())
                .roles(getRoles(userEntity))
                .build();
    }

    private static String[] getRoles(UserEntity user){
        if(user.getRoles() == null)
            return new String[]{"USER"};
        return user.getRoles().split(",");
    }
}
