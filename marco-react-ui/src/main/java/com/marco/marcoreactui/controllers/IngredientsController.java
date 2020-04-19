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

import com.marco.marcoreactui.dto.ingredients.ApiDishRecipe;
import com.marco.marcoreactui.services.interfaces.IngredientsBusinsessLogicInt;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {

	@Autowired
	private IngredientsBusinsessLogicInt service;
	
	@GetMapping
	public ResponseEntity<List<ApiDishRecipe>> getRecepies(){
		List<ApiDishRecipe> list = service.getAllRecipes();
		if(list == null) {
			return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Void> insertIngredient(@RequestBody ApiDishRecipe newRecipe){
		if(service.insertRecipe(newRecipe)) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
	}
	
	@DeleteMapping("/{dishName}")
	public ResponseEntity<Void> deleteRecipe(@PathVariable String dishName){
		if(service.deleteRecipe(dishName)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
	}
	
}
