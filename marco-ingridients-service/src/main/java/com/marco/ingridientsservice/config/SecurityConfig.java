package com.marco.ingridientsservice.config;

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
		
		String[] AUTH_WHITELIST = {
	            // -- swagger ui
	            "/v2/api-docs",
	            "/swagger-resources",
	            "/swagger-resources/**",
	            "/configuration/ui",
	            "/configuration/security",
	            "/swagger-ui.html",
	            "/webjars/**"
	    };
		
		/*
		 * Standard spring security
		 */
		http.csrf().disable()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().authorizeRequests()
			//TODO read those from the DB
			.antMatchers(HttpMethod.GET, "/api/**").hasAnyRole("READ_INGRIDIENTS")
			.antMatchers(HttpMethod.POST, "/api/**").hasAnyRole("SAVE_INGRIDIENTS")
			.antMatchers(HttpMethod.DELETE, "/api/**").hasAnyRole("DELETE_INGRIDIENTS")
			/*
			 * I will make public all the Swagger URLs
			 */
			.antMatchers(HttpMethod.GET, AUTH_WHITELIST).permitAll()
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