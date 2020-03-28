package com.marco.menuservice.errors;

import java.io.Serializable;

/**
 * Simple pojo to represent a Dish service error
 * 
 * @author msolina
 *
 */
public class ServiceError implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
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
