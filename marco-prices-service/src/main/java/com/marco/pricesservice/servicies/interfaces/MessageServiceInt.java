package com.marco.pricesservice.servicies.interfaces;

public interface MessageServiceInt {

    /**
     * It returns the message string, using the "specified" language provided in
     * the HTTP call, or the default one
     * 
     * @param code
     * @param args
     * @return
     */
    public String getMessage(String code, String... args);

}
