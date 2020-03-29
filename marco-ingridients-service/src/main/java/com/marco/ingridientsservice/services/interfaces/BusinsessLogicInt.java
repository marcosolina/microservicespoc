package com.marco.ingridientsservice.services.interfaces;

import java.util.List;

import com.marco.ingridientsservice.errors.MarcoException;
import com.marco.ingridientsservice.model.Ingredient;

/**
 * This interface performs the business logic
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
     * It returns the list of ingredients for the specific dish name
     * 
     * @param name
     * @return
     * @throws MarcoException
     */
    public List<Ingredient> findIngredients(String dishName) throws MarcoException;

    /**
     * It deletes the ingredients list for the provided dish name. If no
     * ingredients exists, then a {@link MarcoException} is thrown
     * 
     * @param dishName
     * @return
     * @throws MarcoException
     */
    public boolean deleteIngredients(String dishName) throws MarcoException;

    /**
     * It returns a list of all the available ingredient
     * 
     * @return
     */
    public List<Ingredient> findAllIngredients();

    /**
     * It checks if the dish is defined in the Dishes service. It throws a
     * {@link MarcoException} if it is not able to contact the Dishes service
     * 
     * @param dishName
     * @return
     * @throws MarcoException
     */
    public boolean checkIfDishExistInDishesService(String dishName) throws MarcoException;
}
