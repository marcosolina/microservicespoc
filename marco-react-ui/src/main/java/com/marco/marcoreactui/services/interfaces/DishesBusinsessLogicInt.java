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
	public boolean insertDish(ApiDish dish);

	public boolean updateDish(ApiDish dish);

	public boolean deleteDish(String name);

	public ApiDish findDish(String name);

	public List<ApiDish> findAllDish();
}
