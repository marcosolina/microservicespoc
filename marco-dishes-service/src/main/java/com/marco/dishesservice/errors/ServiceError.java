package com.marco.dishesservice.errors;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Simple pojo to represent a Dish service error
 * 
 * @author msolina
 *
 */
@ApiModel(value = "Error message", description = "It contains the details of the business error")
public class ServiceError implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Error code")
    private String code;

    @ApiModelProperty(value = "Error message")
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
