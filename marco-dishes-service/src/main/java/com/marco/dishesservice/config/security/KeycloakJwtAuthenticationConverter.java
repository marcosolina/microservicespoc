package com.marco.dishesservice.config.security;

import java.util.Collection;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class KeycloakJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

	private Converter<Jwt, Collection<GrantedAuthority>> grantedAuthoritiesConverter;

	public KeycloakJwtAuthenticationConverter(
			Converter<Jwt, Collection<GrantedAuthority>> grantedAuthoritiesConverter) {
		this.grantedAuthoritiesConverter = grantedAuthoritiesConverter;
	}

	@Override
	public JwtAuthenticationToken convert(Jwt jwt) {

		Collection<GrantedAuthority> authorities = grantedAuthoritiesConverter.convert(jwt);
		String username = getUsernameFrom(jwt);

		return new JwtAuthenticationToken(jwt, authorities, username);
	}

	protected String getUsernameFrom(Jwt jwt) {

		if (jwt.containsClaim("preferred_username")) {
			return jwt.getClaimAsString("preferred_username");
		}

		return jwt.getSubject();
	}

}
