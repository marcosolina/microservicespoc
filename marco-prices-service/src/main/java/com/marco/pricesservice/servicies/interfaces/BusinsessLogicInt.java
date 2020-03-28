package com.marco.pricesservice.servicies.interfaces;

import java.util.List;

import com.marco.pricesservice.errors.MarcoException;
import com.marco.pricesservice.model.Price;

public interface BusinsessLogicInt {
    public Price getPriceForDishName(String dishName) throws MarcoException;

    public boolean insertPrice(Price price) throws MarcoException;

    public boolean updatePrice(Price price) throws MarcoException;

    public List<Price> getAllPrices();

    public boolean deletePriceForDishName(String dishName) throws MarcoException;

    public boolean checkIfDishExistInDishesService(String dishName) throws MarcoException;
}
