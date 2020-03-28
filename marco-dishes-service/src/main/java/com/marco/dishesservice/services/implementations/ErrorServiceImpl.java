package com.marco.dishesservice.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.marco.dishesservice.errors.ServiceError;
import com.marco.dishesservice.errors.MarcoException;
import com.marco.dishesservice.services.interfaces.ErrorServiceInt;
import com.marco.dishesservice.services.interfaces.MessageServiceInt;

public class ErrorServiceImpl implements ErrorServiceInt {

    @Autowired
    private MessageServiceInt msgService;

    @Override
    public ServiceError buildError(String code, String... param) {
        ServiceError de = new ServiceError();
        de.setCode(code);
        de.setMessage(msgService.getMessage(code, param));
        return de;
    }

    @Override
    public MarcoException buildSimpleException(String code, String... param) {
        return new MarcoException(HttpStatus.BAD_REQUEST, msgService.getMessage(code, param));
    }

}
