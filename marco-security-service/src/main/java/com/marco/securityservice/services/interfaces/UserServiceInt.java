package com.marco.securityservice.services.interfaces;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.marco.securityservice.model.UserDocument;

/**
 * This is just the interface that was created to perform some tests
 * 
 * @author msolina
 *
 */
public interface UserServiceInt extends UserDetailsService {

    /**
     * It returns the list off all the available Users
     * 
     * @return
     */
    public List<UserDocument> findAllUsers();

    /**
     * It save the user in the system
     * 
     * @param user
     * @return
     */
    public boolean insertUser(UserDocument user);

    /**
     * It deletes the user from the system
     * 
     * @param userId
     * @return
     */
    public boolean deleteUser(String userId);
}
