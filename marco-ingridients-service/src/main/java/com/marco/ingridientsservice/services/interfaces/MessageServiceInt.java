package com.marco.ingridientsservice.services.interfaces;

/**
 * It reads the messages defined in the resource folder of this project
 * 
 * @author msolina
 *
 */
public interface MessageServiceInt {

    /**
     * It returns the message string, using the "specified" language provided in
     * the HTTP call, or the default one
     * 
     * @param code
     *            -> message code
     * @param args
     *            -> Optional arguments
     * @return {@link String}
     */
    public String getMessage(String code, String... args);

}