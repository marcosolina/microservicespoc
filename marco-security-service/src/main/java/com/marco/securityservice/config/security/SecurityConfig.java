package com.marco.securityservice.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.marco.securityservice.services.interfaces.UserServiceInt;

/**
 * Standard Spring security class
 * 
 * @author msolina
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserServiceInt userDetailService;

    @Autowired
    private PasswordEncoder psswEncoder;

    /**
     * Exposing the Authentication Manager, so it can be used somewhere else,
     * for example in the Authorization Server Config class
     */
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*
         * Fetching the App user details using the custom Service, and the password encoder
         */
        auth
            .userDetailsService(userDetailService)
            .passwordEncoder(psswEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
         * Configuring Spring security to allow everybody to reach the URL
         * to use in order to generate a Token. All the other requests needs to be 
         * authenticated
         */
        http
        .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/oauth/token").permitAll()
            .anyRequest().authenticated()
        .and()
            .csrf().disable()   
            .httpBasic().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); 

    }

}
