package com.marco.dishesservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
            .and()
              .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/**")
                  .hasAuthority("SCOPE_read")
                .antMatchers(HttpMethod.POST, "/api/**")
                  .hasAuthority("SCOPE_write")
                .antMatchers(HttpMethod.PUT, "/api/**")
                  .hasAuthority("SCOPE_write")
                .antMatchers(HttpMethod.DELETE, "/api/**")
                  .hasAuthority("SCOPE_write")
                .anyRequest()
                  .authenticated()
            .and()
              .oauth2ResourceServer()
                .jwt();
    }
}
