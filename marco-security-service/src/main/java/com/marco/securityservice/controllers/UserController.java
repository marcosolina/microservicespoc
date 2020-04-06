package com.marco.securityservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marco.securityservice.dto.ApiUsers;
import com.marco.securityservice.services.interfaces.ApiModelServiceInt;
import com.marco.securityservice.services.interfaces.UserServiceInt;

/**
 * Simple controller to perform some tests. TODO this is a test controller, so
 * once you are happy delete this controller
 * 
 * @author msolina
 *
 */
@RestController
public class UserController {

    @Autowired
    private UserServiceInt userService;

    @Autowired
    private ApiModelServiceInt modelService;

    /**
     * This should only be exposed to admin Users
     * 
     * @return
     */
    @GetMapping("/admin")
    public ResponseEntity<ApiUsers> listUserForAdmin() {
        ApiUsers apiUsers = new ApiUsers();
        userService.findAllUsers().stream().map(modelService::fromUserDocumentToApiUser).forEach(apiUsers::addApiUser);
        return new ResponseEntity<>(apiUsers, HttpStatus.OK);
    }

    /**
     * This should only be exposed to Admin and Guest users
     * 
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<ApiUsers> listUserForAll() {
        ApiUsers apiUsers = new ApiUsers();
        userService.findAllUsers().stream().map(modelService::fromUserDocumentToApiUser).forEach(apiUsers::addApiUser);
        return new ResponseEntity<>(apiUsers, HttpStatus.OK);
    }

}
