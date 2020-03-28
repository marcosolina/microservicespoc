package com.marco.menuservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marco.menuservice.dto.ApiMenu;

@RestController
@RequestMapping("/api")
public class MenuController {

    public ResponseEntity<Void> insertMenu(ApiMenu menu){
        
    }
}
