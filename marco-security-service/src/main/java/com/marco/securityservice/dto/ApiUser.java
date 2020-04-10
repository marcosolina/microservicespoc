package com.marco.securityservice.dto;

import java.io.Serializable;
import java.util.List;

/**
 * This class is used to exhange the information with the REST API
 * 
 * @author msolina
 *
 */
public class ApiUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userName;
    private String passw;
    private List<String> authorities;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

}
