package com.marco.securityservice.services.interfaces;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.marco.securityservice.model.UserDocument;

public interface UserServiceInt extends UserDetailsService {

    public List<UserDocument> findAllUsers();

    public boolean insertUser(UserDocument user);

    public boolean deleteUser(String userId);


}
