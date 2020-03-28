package com.marco.pricesservice.servicies.interfaces;

import org.springframework.http.HttpStatus;

import com.marco.pricesservice.errors.MarcoException;
import com.marco.pricesservice.errors.ServiceError;

/**
 * Simple service to create the Error objects
 * 
 * @author msolina
 *
 */
public interface ErrorServiceInt {
    /**
     * It creates a {@link ServiceError} with the provided error code
     * 
     * @param code
     * @param param
     * @return
     */
    public ServiceError buildError(String code, String... param);

    /**
     * It creates a {@link MarcoException} with the provided code
     * 
     * @param code
     * @param param
     * @return
     */
    public MarcoException buildSimpleException(String code, String... param);
    
    public MarcoException buildSimpleExceptionWithStatus(HttpStatus status, String code, String... param);
}