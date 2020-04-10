package com.marco.securityservice.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

import com.marco.securityservice.config.AppProperties;
import com.marco.securityservice.repositories.ResourcesDocumentRepository;

/**
 * This is the resource server configuration
 * 
 * @author msolina
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private AppProperties properties;

    @Autowired
    private ResourcesDocumentRepository resourcesRepo;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(properties.getResourceId()).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.anonymous().disable().authorizeRequests();

        /*
         * The list of resources, and authorities allowed to access the
         * resources are retrieved from the database. In this way they can be
         * dynamically changed later
         */
        resourcesRepo.findAll().forEach(resource -> {
            //I don not know why they JWT is using "authorities" instead of roles
            registry.antMatchers(resource.getResource()).hasAnyAuthority(resource.getAuthorities().toArray(new String[0]));
        });

        registry.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

}