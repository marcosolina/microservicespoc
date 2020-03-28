package com.marco.menuservice.controllers;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marco.menuservice.dto.ApiMenu;
import com.marco.menuservice.dto.ApiMenus;
import com.marco.menuservice.errors.MarcoException;
import com.marco.menuservice.model.Menu;
import com.marco.menuservice.services.interfaces.BusinsessLogicInt;
import com.marco.menuservice.services.interfaces.ModellingServiceInt;

@RestController
@RequestMapping("/api")
public class MenuController {
    @Autowired
    private BusinsessLogicInt bli;

    @Autowired
    private ModellingServiceInt msi;
    
    @PostMapping()
    public ResponseEntity<Void> insertMenu(@RequestBody ApiMenu menu) throws MarcoException{
        List<Menu> menuList = msi.fromApiMenuToMenuList(menu);
        Iterator<Menu> iter = menuList.iterator();
        while(iter.hasNext()) {
            bli.insertMenu(iter.next());
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    
    @GetMapping("/all")
    public ResponseEntity<ApiMenus> getAllMenus(){
        List<Menu> menuList = bli.findAllMenus();
        return new ResponseEntity<>(msi.fromMenuListToApiMenus(menuList),HttpStatus.OK);
    }
}
