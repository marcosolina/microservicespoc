package com.marco.menuservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * This class defines the configuration of the WebClient. I need this class in
 * oder to request a new JWT token before sending an HTTP request to a different
 * web service. I will retrieve the crendentials using a custom Client
 * Registratio Repository
 * 
 * @author marco
 *
 */
@Configuration
public class WebClientConfig {
	
	@Bean
	@LoadBalanced
	public WebClient.Builder webClientBuilder(ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientRepository authorizedClientRepository) {
		
		ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 = new ServletOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationRepository, authorizedClientRepository);
		return WebClient.builder()
				.apply(oauth2.oauth2Configuration());
	}

	@Bean
	public ClientRegistrationRepository clientRegistrations() {
		return new MarcoClientRegistrationRepository();
	}
}
 
