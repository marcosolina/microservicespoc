package com.marco.menuservice.services.interfaces;

import java.net.URL;
import java.util.Map;

import org.springframework.web.reactive.function.client.ClientResponse;

/**
 * This interface provides the API to perform HTTP requests to a different
 * Service
 * 
 * @author msolina
 *
 */
public interface RestClientInt {

    /**
     * It performs a GET request
     * 
     * @param client registration ID
     * 			  (can be null
     * @param url
     * @param headers
     *            (can be null)
     * @param queryParameters
     *            (can be null)
     * @return
     */
    public ClientResponse performGetRequest(String clientRegistrationID, URL url, Map<String, String> headers, Map<String, String> queryParameters);

    /**
     * It returns the body representation of the HTTP response
     * 
     * @param <T>
     * @param response
     * @param clazz
     *            -> Class to use when parsing the response body
     * @return
     */
    public <T> T getBodyFromResponse(ClientResponse response, Class<T> clazz);

}
