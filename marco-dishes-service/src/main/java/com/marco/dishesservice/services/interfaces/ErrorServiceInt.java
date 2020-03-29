package com.marco.dishesservice.services.interfaces;

import com.marco.dishesservice.errors.ServiceError;
import com.marco.dishesservice.errors.MarcoException;

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
     * @param code -> Error code
     * @param param -> Optional parameters
     * @return {@link ServiceError}
     */
    public ServiceError buildError(String code, String... param);

    /**
     * It creates a {@link MarcoException} with the provided code
     * 
     * @param code -> Error code
     * @param param -> Optional parameters
     * @return {@link MarcoException}
     */
    public MarcoException buildSimpleException(String code, String... param);
}
