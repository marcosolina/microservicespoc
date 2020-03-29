package com.marco.menuservice.errors;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Object returned in the body when a {@link MarcoException} is thrown
 * 
 * @author msolina
 *
 */
@JsonInclude(Include.NON_NULL)
@ApiModel(value = "Api Error", description = "This represent the object that it will be returned in the response body if a business error occurs")
public class ApiErrors {
    @ApiModelProperty(value = "Erros message", required = false)
    private String message;

    @ApiModelProperty(value = "List of errors if multiple errors were generated")
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
