package com.marco.pricesservice.errors;

import java.util.List;

import org.springframework.http.HttpStatus;

/**
 * Custom exception, so I can manage custom responses when an error occurs in the
 * REST API
 * 
 * @author msolina
 *
 */
public class MarcoException extends Exception {
    private static final long serialVersionUID = 1L;
    private final HttpStatus httpStatus;
    private final String message;
    private List<ServiceError> errors;

    public MarcoException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public MarcoException(HttpStatus httpStatus, String message, List<ServiceError> errors) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.errors = errors;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public List<ServiceError> getErrors() {
        return errors;
    }

    public void setErrors(List<ServiceError> errors) {
        this.errors = errors;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
