package com.marco.ingridientsservice.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.marco.ingridientsservice.errors.MarcoException;
import com.marco.ingridientsservice.errors.ServiceError;
import com.marco.ingridientsservice.services.interfaces.ErrorServiceInt;
import com.marco.ingridientsservice.services.interfaces.MessageServiceInt;

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
        return buildSimpleExceptionWithStatus(HttpStatus.BAD_REQUEST, code, param);
    }

    @Override
    public MarcoException buildSimpleExceptionWithStatus(HttpStatus status, String code, String... param) {
        return new MarcoException(status, msgService.getMessage(code, param));
    }

}
