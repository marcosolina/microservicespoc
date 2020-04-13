package com.marco.marcoreactui.services.implementations;

import java.net.URL;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.util.UriBuilder;

import com.marco.marcoreactui.services.interfaces.RestClientInt;

public class RestClientImpl implements RestClientInt{

    @Autowired
    private WebClient.Builder wsClient;
    
    
    public <T> ClientResponse performRequest(HttpMethod method, Optional<String> clientRegistrationID, URL url, Optional<Map<String, String>> headers, Optional<Map<String, String>> queryParameters, MediaType contentType, Optional<?> body) {

        /*
         * Create the request and adds query parameters if provided
         */
        RequestBodySpec rbs = wsClient.build().method(method).uri(uriBuilder -> {
            UriBuilder ub = uriBuilder.scheme(url.getProtocol()).host(url.getHost()).path(url.getPath());
            if (queryParameters.isPresent()) {
                for (Map.Entry<String, String> entry : queryParameters.get().entrySet()) {
                    ub = ub.queryParam(entry.getKey(), entry.getValue());
                }
            }
            return ub.build();

        })
		.contentType(contentType);
        
        if(clientRegistrationID.isPresent()) {
        	rbs = rbs.attributes(ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId(clientRegistrationID.get()));
        }
        
        /*
         * Add HTTP headers if provided
         */
        if(headers.isPresent()) {
            for(Map.Entry<String, String> entry : headers.get().entrySet()) {
                rbs = rbs.header(entry.getKey(), entry.getValue());
            }
        }

        /*
         * Perform the call
         */
        ClientResponse resp = null;
        if (body.isPresent()) {
            resp = rbs.bodyValue(body.get()).exchange().block();
        } else {
            resp = rbs.exchange().block();
        }
        return resp;
    }


    @Override
    public <T> T getBodyFromResponse(ClientResponse response, Class<T> clazz) {
        return response.bodyToMono(clazz).block();
    }


	@Override
	public ClientResponse performGetRequest(Optional<String> clientRegistrationID, URL url, Optional<Map<String, String>> headers, Optional<Map<String, String>> queryParameters) {
		return performRequest(HttpMethod.GET, clientRegistrationID, url, headers, queryParameters, MediaType.APPLICATION_JSON, Optional.empty());
	}


	@Override
	public ClientResponse performPutRequest(Optional<String> clientRegistrationID, URL url, Optional<Map<String, String>> headers, Optional<Map<String, String>> queryParameters, Optional<?> body) {
		return performRequest(HttpMethod.PUT, clientRegistrationID, url, headers, queryParameters, MediaType.APPLICATION_JSON, body);
	}


	@Override
	public ClientResponse performPostRequest(Optional<String> clientRegistrationID, URL url, Optional<Map<String, String>> headers, Optional<Map<String, String>> queryParameters, Optional<?> body) {
		return performRequest(HttpMethod.POST, clientRegistrationID, url, headers, queryParameters, MediaType.APPLICATION_JSON, body);
	}


	@Override
	public ClientResponse performDeleteRequest(Optional<String> clientRegistrationID, URL url, Optional<Map<String, String>> headers, Optional<Map<String, String>> queryParameters, Optional<?> body) {
		return performRequest(HttpMethod.DELETE, clientRegistrationID, url, headers, queryParameters, MediaType.APPLICATION_JSON, body);
	}
	





}
