package com.marco.marcoreactui.services.interfaces;

import java.util.List;

import com.marco.marcoreactui.dto.prices.ApiPrice;

/**
 * This interface defines the business logic for the Prices operations
 * 
 * @author msolina
 *
 */
public interface PricesBusinsessLogicInt {

	/**
	 * It allows you to insert a new Price
	 * 
	 * @param price
	 * @return
	 */
	public boolean insertPrice(ApiPrice price);

	/**
	 * It allow you to update a price
	 * 
	 * @param price
	 * @return
	 */
	public boolean updatePrice(ApiPrice price);

	/**
	 * It allows you to delete a Price for a specific dish name
	 * 
	 * @param name
	 * @return
	 */
	public boolean deletePrice(String name);

	/**
	 * It allows you to find the price for a specific dish
	 * 
	 * @param name
	 * @return
	 */
	public ApiPrice findPrice(String name);

	/**
	 * It will return the list of the prices for all the dishes
	 * 
	 * @return
	 */
	public List<ApiPrice> findAllPrice();
}
