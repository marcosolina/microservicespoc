package com.marco.menuservice.services.interfaces;

import java.net.URL;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.ClientResponse;

public interface RestClientInt {

    /**
     * 
     * @see {@link HttpHeaders}
     * 
     * @param url
     * @param headers
     * @param queryParameters
     * @return
     */
    public ClientResponse performGetRequest(URL url, Map<String, String> headers, Map<String, String> queryParameters);

    /**
     * It returns the body representation of the HTTP response
     * 
     * @param <T>
     * @param response
     * @param clazz
     * @return
     */
    public <T> T getBodyFromResponse(ClientResponse response, Class<T> clazz);

}
