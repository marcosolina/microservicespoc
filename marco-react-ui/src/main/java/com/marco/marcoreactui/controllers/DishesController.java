package com.marco.marcoreactui.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marco.marcoreactui.dto.dishes.ApiDish;

@RestController
@RequestMapping("/dishes")
public class DishesController {
	List<ApiDish> dishes = new ArrayList<ApiDish>();

	@GetMapping
	public List<ApiDish> listAllDishes(){
		return dishes;
	}
	
	@DeleteMapping()
	public ResponseEntity<Void> deleteDish(@RequestBody ApiDish deleteDish){
		int index = -1;
		boolean found = false;
		for (ApiDish apiDish : dishes) {
			index++;
			if(apiDish.getName().equals(deleteDish.getName())) {
				found = true;
				break;
			}
		}
		if(!found) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		dishes.remove(index);
		
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping()
	public ResponseEntity<Void> updateDish(@RequestBody ApiDish updateDish){
		boolean found = false;
		for (ApiDish apiDish : dishes) {
			if(apiDish.getName().equals(updateDish.getName())) {
				apiDish.setCalories(updateDish.getCalories());
				found = true;
				break;
			}
		}
		if(!found) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping()
	public ResponseEntity<Void> insertDish(@RequestBody ApiDish newDish){
		boolean found = false;
		for (ApiDish apiDish : dishes) {
			if(apiDish.getName().equals(newDish.getName())) {
				found = true;
				break;
			}
		}
		if(found) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		dishes.add(newDish);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
}
