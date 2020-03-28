package com.marco.pricesservice.controllers;

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
    public ResponseEntity<ApiPrices> getAllDishes() throws MarcoException {
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
    public ResponseEntity<ApiPrice> findByName(@PathVariable("name") String name) throws MarcoException {
        return new ResponseEntity<>(msi.fromPriceToApiPrice(bli.getPriceForDishName(name)), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insertDish(@RequestBody ApiPrice apiPrice) throws MarcoException {
        bli.insertPrice(msi.fromApiPriceToPrice(apiPrice));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateDish(@RequestBody ApiPrice apiPrice) throws MarcoException {
        bli.updatePrice(msi.fromApiPriceToPrice(apiPrice));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteDish(@PathVariable("name") String name) throws MarcoException {
        bli.deletePriceForDishName(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
