package com.marco.ingridientsservice.controllers;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marco.ingridientsservice.dto.ApiDishRecipe;
import com.marco.ingridientsservice.dto.ApiIngredient;
import com.marco.ingridientsservice.dto.ApiRecipes;
import com.marco.ingridientsservice.errors.MarcoException;
import com.marco.ingridientsservice.services.interfaces.BusinsessLogicInt;
import com.marco.ingridientsservice.services.interfaces.ErrorServiceInt;
import com.marco.ingridientsservice.services.interfaces.ModellingServiceInt;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Standard Spring controller
 * 
 * @author msolina
 *
 */
@RestController()
@RequestMapping("/api")
public class IngridientsController {

    @Autowired
    private BusinsessLogicInt bli;

    @Autowired
    private ModellingServiceInt msi;

    @Autowired
    private ErrorServiceInt errServ;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "It inserts the recepy in the system", response = Void.class, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insertIngredient(@RequestBody ApiDishRecipe apiRecepy) throws MarcoException {
        Iterator<String> iter = apiRecepy.getIngredients().iterator();
        if (!bli.checkIfDishExistInDishesService(apiRecepy.getDishName())) {
            throw errServ.buildSimpleExceptionWithStatus(HttpStatus.BAD_GATEWAY, "ING0004", apiRecepy.getDishName());
        }
        while (iter.hasNext()) {
            bli.insertIngredient(msi.fromApiIngredientToIngredient(new ApiIngredient(apiRecepy.getDishName(), iter.next())));
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "It returns the list of all the available recepies", response = ApiRecipes.class, consumes = MediaType.APPLICATION_JSON_VALUE, responseContainer = "List")
    public ResponseEntity<ApiRecipes> getAllRecipies() throws MarcoException {
        ApiRecipes recipes = msi.fromDishListToApiRecipes(bli.findAllIngredients());
        Iterator<ApiDishRecipe> iterator = recipes.getRecipes().iterator();
        while (iterator.hasNext()) {
            ApiDishRecipe next = iterator.next();
            next.setAvailable(bli.checkIfDishExistInDishesService(next.getDishName()));
        }
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping(value = "/{dishName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "It returns the recepy for the specific dish", response = ApiDishRecipe.class, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiDishRecipe> getDishRecipy(@ApiParam(value = "Name of the dish of which you whant retrieve the recepy", required = true) @PathVariable("dishName") String dishName) throws MarcoException {
        ApiDishRecipe recipe = msi.fromDishToApiDish(bli.findIngredients(dishName));
        recipe.setAvailable(bli.checkIfDishExistInDishesService(dishName));
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @DeleteMapping("/{dishName}")
    @ApiOperation(value = "It deletes the recepy for the specific dish", response = Void.class)
    public ResponseEntity<Void> deleteRecepy(@ApiParam(value = "Name of the dish of which you want to delete the recepy", required = true) @PathVariable("dishName") String dishName) throws MarcoException {
        bli.deleteIngredients(dishName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
