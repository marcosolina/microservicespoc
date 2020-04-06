package com.marco.securityservice.model;

import java.util.List;

/**
 * This represents the Document in the DB which will contain the information of
 * the users of our applications
 * 
 * @author msolina
 *
 */
public class UserDocument {
    private String userName;//unique key
    private String password;
    private List<String> authorities;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

}
