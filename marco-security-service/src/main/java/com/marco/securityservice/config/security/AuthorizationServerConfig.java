package com.marco.securityservice.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.marco.securityservice.services.interfaces.CustomTokenStoreInt;
import com.marco.securityservice.services.interfaces.OauthClientDetailServiceInt;

/**
 * This is the Authorization Server configuration
 * 
 * 
 * @author msolina
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwEncoder;

    @Autowired
    private JwtAccessTokenConverter tokenConverter;

    @Autowired
    private CustomTokenStoreInt customTokenStore;

    @Autowired
    private OauthClientDetailServiceInt oauthService;

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        /*
         * Fetching the client details using a custom Client detail service
         */
        configurer.withClientDetails(oauthService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        /*
         * I don't really know where spring used the following properties, but I
         * had a look at default value inside the security object, and the
         * default is "denyAll()". So by default nobody can pass the security. I
         * am also specifying the password encoder, as we want the password to
         * do not be in clear text
         */
        security
            .tokenKeyAccess("permitAll()")
            .checkTokenAccess("isAuthenticated()")
            .passwordEncoder(passwEncoder);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        /*
         * Telling to Spring Boot to use my custom logic
         */
        endpoints
            .authenticationManager(authenticationManager)
            .tokenStore(customTokenStore)
            .accessTokenConverter(tokenConverter);
    }
}
