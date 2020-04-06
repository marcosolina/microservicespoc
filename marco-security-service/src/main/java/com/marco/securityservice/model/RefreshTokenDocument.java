package com.marco.securityservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import com.marco.securityservice.utils.Utils;

/**
 * This represents the Document in the DB which will contain the information of
 * the Refresh Token issued to the client application
 * 
 * @author msolina
 *
 */
@Document(collection = "refreshStore")
public class RefreshTokenDocument {
    @Id
    private String id;
    private String tokenId;
    private OAuth2RefreshToken token;
    private String authentication;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public OAuth2RefreshToken getToken() {
        return token;
    }

    public void setToken(OAuth2RefreshToken token) {
        this.token = token;
    }

    public OAuth2Authentication getAuthentication() {
        return Utils.deserializeObject(OAuth2Authentication.class, authentication);
    }

    public void setAuthentication(OAuth2Authentication authentication) {
        this.authentication = Utils.serializeObject(authentication);
    }

}
