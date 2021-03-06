package com.marco.pricesservice.controllers;

/**
 * Standard Spring controller
 */
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marco.pricesservice.dto.ApiPrice;
import com.marco.pricesservice.dto.ApiPrices;
import com.marco.pricesservice.errors.MarcoException;
import com.marco.pricesservice.servicies.interfaces.BusinsessLogicInt;
import com.marco.pricesservice.servicies.interfaces.ModellingServiceInt;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
public class PricesController {

    @Autowired
    private BusinsessLogicInt bli;
    @Autowired
    private ModellingServiceInt msi;

    /**
     * It returns the list of all the dises
     * 
     * @return
     * @throws MarcoException
     */
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Returns all the prices", produces = MediaType.APPLICATION_JSON_VALUE, response = ApiPrices.class)
    public ResponseEntity<ApiPrices> getAllPrices() throws MarcoException {
        ApiPrices prices = new ApiPrices();

        bli.getAllPrices().stream().map(price -> msi.fromPriceToApiPrice(price)).forEach(prices::addApiPrice);

        Iterator<ApiPrice> iter = prices.getPrices().iterator();
        while (iter.hasNext()) {
            ApiPrice p = iter.next();
            p.setAvailable(checkIfDishAvailable(p.getDishName()));
        }

        return new ResponseEntity<>(prices, HttpStatus.OK);
    }

    private boolean checkIfDishAvailable(String dishName) throws MarcoException {
        return bli.checkIfDishExistInDishesService(dishName);
    }

    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Returns a single price", produces = MediaType.APPLICATION_JSON_VALUE, response = ApiPrice.class)
    public ResponseEntity<ApiPrice> findByName(@ApiParam(value = "Name of the price to retrieve", required = true)  @PathVariable("name") String name) throws MarcoException {
    	ApiPrice price = msi.fromPriceToApiPrice(bli.getPriceForDishName(name));
    	price.setAvailable(checkIfDishAvailable(name));
        return new ResponseEntity<>(price, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Inserts a price", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insertPrice(@RequestBody ApiPrice apiPrice) throws MarcoException {
        bli.insertPrice(msi.fromApiPriceToPrice(apiPrice));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updates a price", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatePrice(@RequestBody ApiPrice apiPrice) throws MarcoException {
        bli.updatePrice(msi.fromApiPriceToPrice(apiPrice));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{name}")
    @ApiOperation(value = "Deletes a price")
    public ResponseEntity<Void> deletePrice(@ApiParam(value = "Name of the price to delete", required = true)  @PathVariable("name") String name) throws MarcoException {
        bli.deletePriceForDishName(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
