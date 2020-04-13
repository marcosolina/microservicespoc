package com.marco.marcoreactui.services.interfaces;

import java.net.URL;
import java.util.Map;
import java.util.Optional;

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
	 * It perform the GET request
	 * 
	 * @param clientRegistrationID
	 * @param url
	 * @param headers
	 * @param queryParameters
	 * @return
	 */
    public ClientResponse performGetRequest(Optional<String> clientRegistrationID, URL url, Optional<Map<String, String>> headers, Optional<Map<String, String>> queryParameters);
    
    /**
     * It performs the PUT request
     * 
     * @param <T>
     * @param clientRegistrationID
     * @param url
     * @param headers
     * @param queryParameters
     * @param body
     * @return
     */
    public ClientResponse performPutRequest(Optional<String> clientRegistrationID, URL url, Optional<Map<String, String>> headers, Optional<Map<String, String>> queryParameters, Optional<?> body);
    
    /**
     * It performs the POST request
     * 
     * @param <T>
     * @param clientRegistrationID
     * @param url
     * @param headers
     * @param queryParameters
     * @param body
     * @return
     */
    public ClientResponse performPostRequest(Optional<String> clientRegistrationID, URL url, Optional<Map<String, String>> headers, Optional<Map<String, String>> queryParameters, Optional<?> body);
    
    /**
     * It performs the DELETE request
     * 
     * @param <T>
     * @param clientRegistrationID
     * @param url
     * @param headers
     * @param queryParameters
     * @param body
     * @return
     */
    public ClientResponse performDeleteRequest(Optional<String> clientRegistrationID, URL url, Optional<Map<String, String>> headers, Optional<Map<String, String>> queryParameters, Optional<?> body);

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
