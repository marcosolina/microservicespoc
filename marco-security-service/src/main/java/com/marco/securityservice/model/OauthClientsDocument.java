package com.marco.securityservice.model;

import java.util.List;

/**
 * @see <a href=
 *      "https://www.baeldung.com/spring-security-oauth-dynamic-client-registration">OAuth2.0
 *      and Dynamic Client Registration</a>
 * @author msolina
 *
 */

public class OauthClientsDocument {

    /*
     * There are other fields that I can add, but I don't need them at the
     * moment. Please refer to the link in the class java doc . You will learn
     * what they are when needed
     */
    private String client_id;//primary key
    private String client_secret;
    private List<String> scopes;
    private List<String> authorized_grant_types;
    private Integer access_token_validity;
    private Integer refresh_token_validity;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    public List<String> getAuthorized_grant_types() {
        return authorized_grant_types;
    }

    public void setAuthorized_grant_types(List<String> authorized_grant_types) {
        this.authorized_grant_types = authorized_grant_types;
    }

    public Integer getAccess_token_validity() {
        return access_token_validity;
    }

    public void setAccess_token_validity(Integer access_token_validity) {
        this.access_token_validity = access_token_validity;
    }

    public Integer getRefresh_token_validity() {
        return refresh_token_validity;
    }

    public void setRefresh_token_validity(Integer refresh_token_validity) {
        this.refresh_token_validity = refresh_token_validity;
    }

}
