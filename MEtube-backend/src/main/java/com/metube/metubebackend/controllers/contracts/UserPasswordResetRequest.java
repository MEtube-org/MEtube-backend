package com.metube.metubebackend.controllers.contracts;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class UserPasswordResetRequest {
    private String password;
}
