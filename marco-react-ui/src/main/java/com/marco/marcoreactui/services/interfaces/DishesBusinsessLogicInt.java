package com.marco.marcoreactui.services.interfaces;

import java.util.List;

import com.marco.marcoreactui.dto.dishes.ApiDish;

/**
 * This interface defines the business logic for the Dishes operations
 * 
 * @author msolina
 *
 */
public interface DishesBusinsessLogicInt {

	/**
	 * It inserts a new Dish
	 * 
	 * @param dish
	 * @return
	 */
	public boolean insertDish(ApiDish dish);

	/**
	 * It update an existing dish
	 * 
	 * @param dish
	 * @return
	 */
	public boolean updateDish(ApiDish dish);

	/**
	 * It delete the dish definition
	 * 
	 * @param name
	 * @return
	 */
	public boolean deleteDish(String name);

	/**
	 * It returns the dish definition
	 * 
	 * @param name
	 * @return
	 */
	public ApiDish findDish(String name);

	/**
	 * It lists all the available dish definitions
	 * 
	 * @return
	 */
	public List<ApiDish> findAllDish();
}
