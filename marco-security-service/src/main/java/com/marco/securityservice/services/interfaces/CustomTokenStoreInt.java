package com.marco.securityservice.services.interfaces;

import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * Interface to implement when you want to use a Custom Token Store
 * 
 * @author msolina
 *
 */
public interface CustomTokenStoreInt extends TokenStore {
}
