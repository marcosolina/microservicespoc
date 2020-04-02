package com.marco.securityservice.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ApiUsers implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<ApiUser> users;

    public boolean addApiUser(ApiUser user) {
        if (this.users == null) {
            this.users = new ArrayList<>();
        }
        return this.users.add(user);
    }

    public List<ApiUser> getUsers() {
        return users;
    }

    public void setUsers(List<ApiUser> users) {
        this.users = users;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
