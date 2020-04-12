package com.marco.dishesservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationConverter jwtConverter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		/*
		 * Standard spring security
		 */
		http.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().authorizeRequests()
			//TODO read those from the DB
			.antMatchers(HttpMethod.GET, "/api/**").hasAnyRole("READ_DISH")
			.antMatchers(HttpMethod.POST, "/api/**").hasAnyRole("SAVE_DISH")
			.antMatchers(HttpMethod.PUT, "/api/**").hasAnyRole("SAVE_DISH")
			.antMatchers(HttpMethod.DELETE, "/api/**").hasAnyRole("DELETE_DISH")
			.anyRequest().authenticated()
			.and()
			/*
			 * Telling that this is a resource server, so I will receive a token
			 * that needs to be checked
			 */
			.oauth2ResourceServer()
			.jwt()
			.jwtAuthenticationConverter(jwtConverter);
	}
}