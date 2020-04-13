package com.marco.menuservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.marco.menuservice.utils.PricesConstants;

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
		/*
		 * Registering the "client" credential to use when I want to retrieve the token
		 */
		ClientRegistration clientRegistration = ClientRegistration
				.withRegistrationId(PricesConstants.TOKEN_DISHES_REGISTRATION_ID)
				.clientId("menu-service").clientSecret("1bbd2456-5f13-4525-b920-15f15672f241")
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
				.clientAuthenticationMethod(ClientAuthenticationMethod.POST)
				.tokenUri("http://localhost:8091/auth/realms/marco-realm/protocol/openid-connect/token")
				.build();

		//TODO retrieve from a database//TODO retrieve from a database
		return new InMemoryClientRegistrationRepository(clientRegistration);
	}
}
 
