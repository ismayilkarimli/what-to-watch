package com.whattowatch.WhatToWatch.exception;

import org.springframework.http.HttpStatus;

public class ContentException extends RuntimeException {

    public ContentException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Error message: " + super.getMessage();
    }
}
