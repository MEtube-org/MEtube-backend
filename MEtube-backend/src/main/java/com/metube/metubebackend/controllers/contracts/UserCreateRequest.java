package com.metube.metubebackend.controllers.contracts;

import com.metube.metubebackend.entities.UserEntity;
import lombok.Getter;
import org.springframework.beans.BeanUtils;

@Getter
public class UserCreateRequest {
    private String username;
    private String password;
    public UserEntity toUser(){
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(this, userEntity);
        return userEntity;
    }
}
