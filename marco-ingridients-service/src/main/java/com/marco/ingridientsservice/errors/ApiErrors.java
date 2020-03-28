package com.marco.ingridientsservice.errors;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Object returned in the body when a {@link MarcoException} is thrown
 * 
 * @author msolina
 *
 */
@JsonInclude(Include.NON_NULL)
public class ApiErrors {
    private String message;
    private List<ServiceError> errors;

    public ApiErrors() {

    }

    public ApiErrors(List<ServiceError> errors, String message) {
        this.errors = errors;
        this.message = message;
    }

    public List<ServiceError> getErrors() {
        return errors;
    }

    public void setErrors(List<ServiceError> errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
