package com.marco.securityservice.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This represents the Document in the DB which will contain the information of
 * who is allowed to access a specific resource
 * 
 * @author msolina
 *
 */
@Document(collection = "resources")
public class ResourcesDocument {
    private String resource;
    private List<String> authorities;

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

}
