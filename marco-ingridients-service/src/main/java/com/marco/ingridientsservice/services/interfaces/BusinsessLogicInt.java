package com.marco.ingridientsservice.services.interfaces;

import java.util.List;

import com.marco.ingridientsservice.errors.MarcoException;
import com.marco.ingridientsservice.model.Ingredient;

/**
 * This interface allow the CRUD operation in the DATABASE
 * 
 * @author msolina
 *
 */
public interface BusinsessLogicInt {
    /**
     * It inserts the new ingredient. If the ingredient already exist, then a
     * {@link MarcoException} is thrown
     * 
     * @param dish
     * @return
     * @throws MarcoException
     */
    public boolean insertIngredient(Ingredient ingridient) throws MarcoException;


    /**
     * It deletes the ingredient with the provided name. If the ingredient do
     * not exist then a {@link MarcoException} is thrown
     * 
     * @param name
     * @return
     * @throws MarcoException
     */
    public boolean deleteIngredient(Ingredient ingridient) throws MarcoException;

    /**
     * It returns the ingredient with the provided name. If the ingredient do
     * not exist then a {@link MarcoException} is thrown
     * 
     * @param name
     * @return
     * @throws MarcoException
     */
    public List<Ingredient> findIngredients(String dishName) throws MarcoException;
    
    public boolean deleteIngredients(String dishName) throws MarcoException;

    /**
     * It returns a list of all the available ingredient
     * 
     * @return
     */
    public List<Ingredient> findAllIngredients();

    public boolean checkIfDishExistInDishesService(String dishName) throws MarcoException;
}
