package com.marco.securityservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.marco.securityservice.dto.ApiUser;
import com.marco.securityservice.dto.ApiUsers;
import com.marco.securityservice.services.interfaces.ApiModelServiceInt;
import com.marco.securityservice.services.interfaces.UserServiceInt;

@RestController
public class UserController {
    @Autowired
    private UserServiceInt userService;

    @Autowired
    private ApiModelServiceInt modelService;

    @GetMapping("/all")
    public ResponseEntity<ApiUsers> listUser() {
        ApiUsers apiUsers = new ApiUsers();
        userService.findAllUsers().stream().map(modelService::fromUserDocumentToApiUser).forEach(apiUsers::addApiUser);
        return new ResponseEntity<>(apiUsers, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<Void> create(@RequestBody ApiUser apiUser) {
        userService.insertUser(modelService.fromApiUserToUserDocument(apiUser));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") String userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
