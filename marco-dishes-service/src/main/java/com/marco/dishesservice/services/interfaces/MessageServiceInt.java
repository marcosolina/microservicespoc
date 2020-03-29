package com.marco.dishesservice.services.interfaces;

public interface MessageServiceInt {

    /**
     * It returns the message string, using the "specified" language provided in
     * the HTTP call, or the default one
     * 
     * @param code -> message code
     * @param args -> Optional arguments
     * @return {@link String}
     */
    public String getMessage(String code, String... args);

}
