package com.marco.ingridientsservice.config;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class CustomRealmRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

	@SuppressWarnings("unchecked")
	public Collection<GrantedAuthority> convert(final Jwt jwt) {
		/*
		 * Extracting the roles from the TOKEN
		 * TODO see if there is a better way to do this
		 */
		Map<String, Object> resource_access = (Map<String, Object>) jwt.getClaims().get("resource_access");
		Map<String, Object> thisAppRoles = (Map<String, Object>) resource_access.get("ingridients-service");
		List<String> roles = (List<String>) thisAppRoles.get("roles");
		return roles.stream().map(roleName -> "ROLE_" + roleName).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

}
