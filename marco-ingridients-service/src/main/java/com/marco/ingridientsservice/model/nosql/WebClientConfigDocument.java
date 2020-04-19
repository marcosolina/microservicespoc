package com.marco.ingridientsservice.model.nosql;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "webclients")
public class WebClientConfigDocument {

	@Id
	private String registrationId;
	private String clientId;
	private String clientSecret;
	private String authorizationGrantType;
	private String clientAuthenticationMethod;
	private String tokenUri;

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getAuthorizationGrantType() {
		return authorizationGrantType;
	}

	public void setAuthorizationGrantType(String authorizationGrantType) {
		this.authorizationGrantType = authorizationGrantType;
	}

	public String getClientAuthenticationMethod() {
		return clientAuthenticationMethod;
	}

	public void setClientAuthenticationMethod(String clientAuthenticationMethod) {
		this.clientAuthenticationMethod = clientAuthenticationMethod;
	}

	public String getTokenUri() {
		return tokenUri;
	}

	public void setTokenUri(String tokenUri) {
		this.tokenUri = tokenUri;
	}

}
