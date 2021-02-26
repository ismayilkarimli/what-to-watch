package com.whattowatch.WhatToWatch.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ContentExceptionHandler {

    @ExceptionHandler(value = {IllegalSearchQueryException.class})
    public ModelAndView handleIDException(ContentException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/error/error");
        modelAndView.addObject("error", e.getMessage());
        return modelAndView;
    }
}
