package com.metube.metubebackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="Access Denied")
public class ForbiddenException extends RuntimeException {
    private static final String forbiddenTemplate = "Entity: %s access denied";

    public ForbiddenException(String entity) {
        super(String.format(forbiddenTemplate, entity));
    }
}