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
	public boolean insertPrice(ApiPrice price);

	public boolean updatePrice(ApiPrice price);

	public boolean deletePrice(String name);

	public ApiPrice findPrice(String name);

	public List<ApiPrice> findAllPrice();
}
