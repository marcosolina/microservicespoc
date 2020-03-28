package com.marco.ingridientsservice.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Custom Response exception handler. In this way I can return in the
 * appropriate way business exceptions
 * 
 * @author msolina
 *
 */
@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { MarcoException.class })
    public ResponseEntity<ApiErrors> handleIxicoExceptions(MarcoException ex, WebRequest request) {
        return new ResponseEntity<>(new ApiErrors(ex.getErrors(), ex.getMessage()), ex.getHttpStatus());
    }

}
