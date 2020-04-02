package com.marco.securityservice.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

import com.marco.securityservice.config.AppProperties;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private AppProperties properties;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(properties.getResourceId()).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.anonymous().disable().authorizeRequests().antMatchers("/users/**").access("hasRole('ADMIN')").and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

}
