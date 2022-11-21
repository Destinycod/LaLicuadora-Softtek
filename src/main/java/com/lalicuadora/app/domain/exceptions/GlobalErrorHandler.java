package com.lalicuadora.app.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalErrorHandler {
    @ExceptionHandler(InvalidBaseProductException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public String invalidBaseProduct(InvalidBaseProductException ex) {
        return "El producto " + ex.getErrorName() + " esta mal: " + ex.getErrorType();
    }
}
