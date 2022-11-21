package com.lalicuadora.app.domain.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InvalidBaseProductException extends Exception{

    private String errorName;

    private String errorType;

    public InvalidBaseProductException(String string, String errorName){
        super();
        this.errorName = errorName;
        this.errorType = string;
    }
}