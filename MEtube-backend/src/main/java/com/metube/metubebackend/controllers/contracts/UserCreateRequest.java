package com.metube.metubebackend.controllers.contracts;

import com.metube.metubebackend.entities.User;
import lombok.Getter;
import org.springframework.beans.BeanUtils;

@Getter
public class UserCreateRequest {
    private String password;
    private String username;

    public User toUser(){
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }
}
