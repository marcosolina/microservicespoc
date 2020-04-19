package com.marco.marcoreactui.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marco.marcoreactui.dto.menu.ApiMenu;
import com.marco.marcoreactui.services.interfaces.MenusBusinsessLogicInt;


/**
 * Standard Spring controller to manage all the actions coming from the Menu
 * View
 * 
 * @author marco
 *
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenusBusinsessLogicInt service;

	@GetMapping
	public ResponseEntity<List<ApiMenu>> getAllMenus() {
		List<ApiMenu> list = service.getAllMenus();
		if (list == null) {
			return new ResponseEntity<List<ApiMenu>>(HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<List<ApiMenu>>(list, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Void> insertNewMenu(@RequestBody ApiMenu newMenu) {
		if (service.insertMenu(newMenu)) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
	}

	@DeleteMapping("/{menuName}")
	public ResponseEntity<Void> deleteMenu(@PathVariable("menuName") String menuName) {
		if (service.deleteMenu(menuName)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
	}

}
