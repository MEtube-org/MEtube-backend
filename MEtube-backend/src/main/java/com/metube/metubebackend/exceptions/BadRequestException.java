package com.metube.metubebackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Bad Request")
public class BadRequestException {
    public BadRequestException() { super(); }
}
