package com.marco.pricesservice.servicies.implementations;

import java.net.URL;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.util.UriBuilder;

import com.marco.pricesservice.servicies.interfaces.RestClientInt;

public class RestClientImpl implements RestClientInt{

    @Autowired
    private WebClient.Builder wsClient;
    
    @Override
    public ClientResponse performGetRequest(String clientRegistrationID, URL url, Map<String, String> headers, Map<String, String> queryParameters) {
        return performRequest(HttpMethod.GET, clientRegistrationID, url, headers, queryParameters, MediaType.APPLICATION_JSON, null);
    }

    
    public ClientResponse performRequest(HttpMethod method, String clientRegistrationID, URL url, Map<String, String> headers, Map<String, String> queryParameters, MediaType contentType, Object body) {

        /*
         * Create the request and adds query parameters if provided
         */
        RequestBodySpec rbs = wsClient.build().method(method).uri(uriBuilder -> {
            UriBuilder ub = uriBuilder.scheme(url.getProtocol()).host(url.getHost()).path(url.getPath());
            if (queryParameters != null) {
                for (Map.Entry<String, String> entry : queryParameters.entrySet()) {
                    ub = ub.queryParam(entry.getKey(), entry.getValue());
                }
            }
            return ub.build();

        })
		.contentType(contentType);
        
        if(clientRegistrationID != null) {
        	rbs = rbs.attributes(ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId(clientRegistrationID));
        }
        
        /*
         * Add HTTP headers if provided
         */
        if(headers != null) {
            for(Map.Entry<String, String> entry : headers.entrySet()) {
                rbs = rbs.header(entry.getKey(), entry.getValue());
            }
        }

        /*
         * Perform the call
         */
        ClientResponse resp = null;
        if (body != null) {
            resp = rbs.bodyValue(body).exchange().block();
        } else {
            resp = rbs.exchange().block();
        }
        return resp;
    }


    @Override
    public <T> T getBodyFromResponse(ClientResponse response, Class<T> clazz) {
        return response.bodyToMono(clazz).block();
    }
}
