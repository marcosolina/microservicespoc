package com.marco.marcoreactui.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.marco.marcoreactui.services.interfaces.DishesBusinsessLogicInt;

@RestController
@RequestMapping("/dishes")
public class DishesController {
	
	@Autowired
	private DishesBusinsessLogicInt dishService;

	@GetMapping
	public List<ApiDish> listAllDishes(){
		return dishService.findAllDish();
	}
	
	@DeleteMapping()
	public ResponseEntity<Void> deleteDish(@RequestBody ApiDish deleteDish){
		if(dishService.deleteDish(deleteDish.getName())) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
	}
	
	@PutMapping()
	public ResponseEntity<Void> updateDish(@RequestBody ApiDish updateDish){
		if(dishService.updateDish(updateDish)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
	}
	
	@PostMapping()
	public ResponseEntity<Void> insertDish(@RequestBody ApiDish newDish){
		if(dishService.insertDish(newDish)) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
	}
	
}
