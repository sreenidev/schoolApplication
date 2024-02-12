package com.institute.school.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SchoolException extends RuntimeException{
    public SchoolException(String message){
        super(message);
    }
}
