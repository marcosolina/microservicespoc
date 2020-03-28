package com.marco.dishesservice.services.interfaces;

import com.marco.dishesservice.dto.ApiDish;
import com.marco.dishesservice.model.Dish;

/**
 * Simple service to help the controller to retrieve the data from the data
 * source
 * 
 * @author msolina
 *
 */
public interface ModellingServiceInt {

    /**
     * It converts a DB Dish {@link Dish} into an API Dish {@link ApiDish}
     * 
     * @param dish
     * @return
     */
    public ApiDish fromDishToApiDish(Dish dish);

    /**
     * It converts an API Dish {@link ApiDish} into a DB Dish {@link Dish}
     * 
     * @param apiDish
     * @return
     */
    public Dish fromApiDishToDish(ApiDish apiDish);
}
