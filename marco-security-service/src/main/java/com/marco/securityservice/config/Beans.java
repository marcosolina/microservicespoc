package com.marco.securityservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.marco.securityservice.services.implementations.ApiModelServiceImp;
import com.marco.securityservice.services.implementations.CustomTokenStoreImp;
import com.marco.securityservice.services.implementations.OauthClientDetailServiceImp;
import com.marco.securityservice.services.implementations.UserServiceImp;
import com.marco.securityservice.services.interfaces.ApiModelServiceInt;
import com.marco.securityservice.services.interfaces.CustomTokenStoreInt;
import com.marco.securityservice.services.interfaces.OauthClientDetailServiceInt;
import com.marco.securityservice.services.interfaces.UserServiceInt;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class Beans {
    @Value("${spring.data.mongodb.host}")
    private String dbHost;
    @Value("${spring.data.mongodb.port}")
    private String dbPort;
    
    @Autowired
    private AppProperties properties;

    /**
     * It returns the instance of the MongoDb client.
     * You can use this bean to get the MongoDB connection if
     * you need to perform some custom queries 
     * 
     * @return
     */
    @Bean
    public MongoClient getMongoClient() {
        return MongoClients.create(String.format("mongodb://%s:%s", dbHost, dbPort));
    }

    @Bean
    public UserServiceInt getUserServiceInt() {
        return new UserServiceImp();
    }
    
    @Bean
    public OauthClientDetailServiceInt getOauthClientDetailServiceInt() {
        return new OauthClientDetailServiceImp();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(properties.getPrivateKey());
        return converter;
    }
    
    @Bean
    public CustomTokenStoreInt getCustomTokenStoreInt() {
        return new CustomTokenStoreImp();
    }

    
    @Bean
    public JwtTokenStore tokenStore() {
        /*
         * If you need to create a castom token store
         * https://blog.couchbase.com/custom-token-store-spring-securtiy-oauth2/
         */
        return new JwtTokenStore(tokenEnhancer());
    }

    @Bean
    public ApiModelServiceInt getApiModelServiceInt() {
        return new ApiModelServiceImp();
    }

    /**
     * This block will allow us to make requests using CORS (Cross-Origin Resource Sharing)
     * 
     * @see <a href="https://blog.couchbase.com/spring-security-oauth2_authentication/">Spring security tutorial</a>
     * @return
     */
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}
