package com.marco.dishesservice.controllers;

import java.util.Optional;

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

import com.marco.dishesservice.dto.ApiDish;
import com.marco.dishesservice.dto.ApiDishes;
import com.marco.dishesservice.errors.MarcoException;
import com.marco.dishesservice.model.Dish;
import com.marco.dishesservice.services.interfaces.BusinsessLogicInt;
import com.marco.dishesservice.services.interfaces.ModellingServiceInt;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * This class provides the REST API It returns the appropriate HTTP status code:
 * 
 * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Status">HTTP
 *      response status codes</a>
 * 
 * @author msolina
 *
 */
@RestController()
@RequestMapping("/api")
public class DishesController {

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
    @ApiOperation(value = "Returns a list off all the dishes", response = ApiDishes.class, responseContainer = "List", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiDishes> getAllDishes() throws MarcoException {
        ApiDishes dishes = new ApiDishes();

        bli.findAllDish().stream().map(dish -> msi.fromDishToApiDish(dish)).forEach(dishes::addApiDish);

        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    /**
     * It searches for the dish with name...
     * 
     * @param name
     * @return
     * @throws MarcoException
     */
    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find the dish by name", response = ApiDish.class, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiDish> findByName(@ApiParam(value = "Name of the dish to retrieve", required = true) @PathVariable("name") String name) throws MarcoException {
        Optional<Dish> dish = bli.findDish(name);

        if (dish.isPresent()) {
            return new ResponseEntity<>(msi.fromDishToApiDish(dish.get()), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * It inserts a new dish
     * 
     * @param apiDish
     * @return
     * @throws MarcoException
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Insert a new Dish", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insertDish(@RequestBody ApiDish apiDish) throws MarcoException {
        bli.insertDish(msi.fromApiDishToDish(apiDish));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * It updates an existing dish
     * 
     * @param apiDish
     * @return
     * @throws MarcoException
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updates an existing dish", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateDish(@RequestBody ApiDish apiDish) throws MarcoException {
        bli.updateDish(msi.fromApiDishToDish(apiDish));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * It deletes an existing dish
     * 
     * @param name
     * @return
     * @throws MarcoException
     */
    @DeleteMapping("/{name}")
    @ApiOperation(value = "Deletes an existing dish")
    public ResponseEntity<Void> deleteDish(@ApiParam(value = "The name of the dish to delete", required = true) @PathVariable("name") String name) throws MarcoException {
        bli.deleteDish(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
