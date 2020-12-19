package com.whattowatch.WhatToWatch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IllegalSearchQueryException extends ContentException {

    public IllegalSearchQueryException(String message) {
        super(message);
    }
}
