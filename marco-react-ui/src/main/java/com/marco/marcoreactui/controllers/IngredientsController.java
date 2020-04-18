package com.marco.marcoreactui.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marco.marcoreactui.dto.ingredients.ApiDishRecipe;
import com.marco.marcoreactui.dto.ingredients.ApiIngredient;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {

	private List<ApiDishRecipe> list = new ArrayList<>();
	
	@GetMapping
	public ResponseEntity<List<ApiDishRecipe>> getRecepies(){
		list = new ArrayList<ApiDishRecipe>();
		for (int i = 0; i < 5; i++) {
			ApiDishRecipe r = new ApiDishRecipe();
			r.setDishName("Dish" + i);
			for(int j = 0; j < 5; j++) {
				r.addIngredient("Ing"+j);
			}
			list.add(r);
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Void> insertIngredient(@RequestBody ApiIngredient newIngredient){
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
}
