package com.utsavsharma.smartContactManager.helper;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @file GlobalExceptionHandler.java
 *       Author: Utsav Sharma
 *       Date: 23-08-2024
 *       Time: 15:17:34
 */

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e, Model model) {
        System.out.println("Exception: " + e.getMessage());

        return "login";

    }

}
