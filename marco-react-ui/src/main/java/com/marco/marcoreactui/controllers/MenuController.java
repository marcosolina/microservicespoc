package com.marco.marcoreactui.controllers;

import java.util.ArrayList;
import java.util.List;

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

@RestController
@RequestMapping("/menu")
public class MenuController {

	private List<ApiMenu> list = new ArrayList<ApiMenu>();
	
	@GetMapping
	public ResponseEntity<List<ApiMenu>> getAllMenus(){
		list = new ArrayList<ApiMenu>();
		for(int i = 0; i < 5; i++) {
			ApiMenu m = new ApiMenu();
			m.setMenuName("Menu" + i);
			for(int j = 0; j < 5; j++) {
				m.addDishName("Dish" + j);
			}
			list.add(m);
		}
		
		return new ResponseEntity<List<ApiMenu>>(list, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Void> insertNewMenu(@RequestBody ApiMenu newMenu){
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{menuName}")
	public ResponseEntity<Void> deleteMenu(@PathVariable("menuName") String menuName){
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
