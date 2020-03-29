package com.marco.menuservice.controllers;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marco.menuservice.dto.ApiMenu;
import com.marco.menuservice.dto.ApiMenus;
import com.marco.menuservice.errors.MarcoException;
import com.marco.menuservice.model.Menu;
import com.marco.menuservice.services.interfaces.BusinsessLogicInt;
import com.marco.menuservice.services.interfaces.ErrorServiceInt;
import com.marco.menuservice.services.interfaces.ModellingServiceInt;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Standard Spring controller
 * 
 * @author msolina
 *
 */
@RestController
@RequestMapping("/api")
public class MenuController {
    @Autowired
    private BusinsessLogicInt bli;

    @Autowired
    private ModellingServiceInt msi;
    
    @Autowired
    private ErrorServiceInt errServ;

    @PostMapping()
    @ApiOperation(value = "Inserts a new Menu", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insertMenu(@RequestBody ApiMenu menu) throws MarcoException {
        List<Menu> menuList = msi.fromApiMenuToMenuList(menu);
        Iterator<Menu> iter = menuList.iterator();
        while (iter.hasNext()) {
            Menu m = iter.next();
            if(!bli.checkIfDishExistInDishesService(m.getId().getDishName())) {
                throw errServ.buildSimpleExceptionWithStatus(HttpStatus.BAD_GATEWAY, "MENU0005", m.getId().getDishName());
            }
        }
        iter = menuList.iterator();
        while (iter.hasNext()) {
            bli.insertMenu(iter.next());
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @ApiOperation(value = "Returns a list off all the Menus", response = ApiMenus.class, responseContainer = "List", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiMenus> getAllMenus() {
        List<Menu> menuList = bli.findAllMenus();
        return new ResponseEntity<>(msi.fromMenuListToApiMenus(menuList), HttpStatus.OK);
    }
    
    @GetMapping("/{menuName}")
    @ApiOperation(value = "Returns the menu definition", response = ApiMenu.class, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiMenu> getMenu(@ApiParam(value = "Name of the menu to retrieve", required = true) @PathVariable("menuName") String menuName) throws MarcoException {
        return new ResponseEntity<>(msi.fromMenuListToApiMenu(bli.findDishesForMenu(menuName)), HttpStatus.OK);
    }

    @DeleteMapping("/{menuName}")
    public ResponseEntity<Void> deleteMenu(@ApiParam(value = "Name of the menu to delete", required = true) @PathVariable("menuName") String menuName) throws MarcoException {
        bli.deleteAllDishesForMenu(menuName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
