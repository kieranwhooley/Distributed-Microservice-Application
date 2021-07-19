package edu.ait.capitalservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CapitalNotFoundException extends RuntimeException{
    public CapitalNotFoundException(String message) {
        super(message);
    }
}
