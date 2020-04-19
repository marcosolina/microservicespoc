package com.marco.pricesservice.servicies.interfaces;

import java.util.List;

import com.marco.pricesservice.errors.MarcoException;
import com.marco.pricesservice.model.sql.Price;

/**
 * This interface performs the business logic
 * 
 * @author msolina
 *
 */
public interface BusinsessLogicInt {
    /**
     * It returns the {@link Price} for the provided dish name. If the price do
     * not exists then a {@link MarcoException} is thrown
     * 
     * @param dishName
     * @return
     * @throws MarcoException
     */
    public Price getPriceForDishName(String dishName) throws MarcoException;

    /**
     * It inserts a new {@link Price}. If the price already exists, then a
     * {@link MarcoException} is thrown
     * 
     * @param price
     * @return
     * @throws MarcoException
     */
    public boolean insertPrice(Price price) throws MarcoException;

    /**
     * It updates the {@link Price}. If the price do not exists, then a
     * {@link MarcoException} is thrown
     * 
     * @param price
     * @return
     * @throws MarcoException
     */
    public boolean updatePrice(Price price) throws MarcoException;

    /**
     * It returns a list of all the available prices
     * 
     * @return
     */
    public List<Price> getAllPrices();

    /**
     * It deletes the {@link Price}. If the price do not exists, then a
     * {@link MarcoException} is thrown
     * 
     * @param price
     * @return
     * @throws MarcoException
     */
    public boolean deletePriceForDishName(String dishName) throws MarcoException;

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
