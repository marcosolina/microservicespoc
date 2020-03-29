package com.marco.dishesservice.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.marco.dishesservice.errors.MarcoException;
import com.marco.dishesservice.model.Dish;

/**
 * This interface performs the business logic
 * 
 * @author msolina
 *
 */
public interface BusinsessLogicInt {
    /**
     * It inserts the new Dish. If the dish already exist, then a
     * {@link MarcoException} is thrown
     * 
     * @param dish
     * @return
     * @throws MarcoException
     */
    public boolean insertDish(Dish dish) throws MarcoException;

    /**
     * It update an existing dish. If the dish do not exist then a
     * {@link MarcoException} is thrown
     * 
     * @param dish
     * @return
     * @throws MarcoException
     */
    public boolean updateDish(Dish dish) throws MarcoException;

    /**
     * It deletes the dish with the provided name. If the dish do not exist then
     * a {@link MarcoException} is thrown
     * 
     * @param name
     * @return
     * @throws MarcoException
     */
    public boolean deleteDish(String name) throws MarcoException;

    /**
     * It returns the dish with the provided name. If the dish do not exist then
     * a {@link MarcoException} is thrown
     * 
     * @param name
     * @return
     * @throws MarcoException
     */
    public Optional<Dish> findDish(String name) throws MarcoException;

    /**
     * It returns a list of all the available dishes
     * 
     * @return
     */
    public List<Dish> findAllDish() ;
}
