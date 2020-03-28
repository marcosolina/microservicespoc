package com.marco.dishesservice.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.marco.dishesservice.services.interfaces.MessageServiceInt;

public class MessageServiceImpl implements MessageServiceInt{
    @Autowired
    private MessageSource msg;

    @Override
    public String getMessage(String code, String... args) {
        return String.format("%s", msg.getMessage(code, args, code, LocaleContextHolder.getLocale()));
    }

}
