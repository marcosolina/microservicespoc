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

import com.marco.marcoreactui.dto.prices.ApiPrice;
import com.marco.marcoreactui.services.interfaces.PricesBusinsessLogicInt;

/**
 * Standard Spring controller to manage all the actions coming from the Prices
 * View
 * 
 * @author marco
 *
 */
@RestController
@RequestMapping("/prices")
public class PricesController {
	
	@Autowired
	private PricesBusinsessLogicInt dishService;

	@GetMapping
	public List<ApiPrice> listAllPrices(){
		return dishService.findAllPrice();
	}
	
	@DeleteMapping()
	public ResponseEntity<Void> deletePrice(@RequestBody ApiPrice deletePrice){
		if(dishService.deletePrice(deletePrice.getDishName())) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
	}
	
	@PutMapping()
	public ResponseEntity<Void> updatePrice(@RequestBody ApiPrice updatePrice){
		if(dishService.updatePrice(updatePrice)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
	}
	
	@PostMapping()
	public ResponseEntity<Void> insertPrice(@RequestBody ApiPrice newPrice){
		if(dishService.insertPrice(newPrice)) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
	}
	
}
