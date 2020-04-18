package com.marco.pricesservice.config;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import com.marco.pricesservice.model.WebClientConfigDocument;
import com.marco.pricesservice.repositories.WebClientsRepository;

/**
 * This is my custom Client Registration Repository. I will use this class to
 * retrieve the Clients data from a Database
 * 
 * @author marco
 *
 */
public class MarcoClientRegistrationRepository implements ClientRegistrationRepository, Iterable<ClientRegistration> {

	@Autowired
	private WebClientsRepository repo;

	@Override
	public Iterator<ClientRegistration> iterator() {
		List<WebClientConfigDocument> list = repo.findAll();

		List<ClientRegistration> listCr = list.stream().map(this::fromDocumentToClientRegistration)
				.collect(Collectors.toList());
		return listCr.iterator();
	}

	@Override
	public ClientRegistration findByRegistrationId(String registrationId) {
		Optional<WebClientConfigDocument> clientDoc = repo.findById(registrationId);
		if (clientDoc.isPresent()) {
			return fromDocumentToClientRegistration(clientDoc.get());
		}
		return null;
	}

	private ClientRegistration fromDocumentToClientRegistration(WebClientConfigDocument doc) {
		ClientRegistration client = ClientRegistration.withRegistrationId(doc.getRegistrationId())
				.clientId(doc.getClientId()).clientSecret(doc.getClientSecret())
				.authorizationGrantType(new AuthorizationGrantType(doc.getAuthorizationGrantType()))
				.clientAuthenticationMethod(new ClientAuthenticationMethod(doc.getClientAuthenticationMethod()))
				.tokenUri(doc.getTokenUri()).build();
		return client;
	}

}
