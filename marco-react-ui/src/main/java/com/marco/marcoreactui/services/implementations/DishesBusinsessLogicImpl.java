package com.marco.marcoreactui.services.implementations;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;

import com.marco.marcoreactui.config.properties.DishesServiceProperties;
import com.marco.marcoreactui.dto.dishes.ApiDish;
import com.marco.marcoreactui.dto.dishes.ApiDishes;
import com.marco.marcoreactui.services.interfaces.DishesBusinsessLogicInt;
import com.marco.marcoreactui.services.interfaces.RestClientInt;
import com.marco.marcoreactui.utils.PricesConstants;

public class DishesBusinsessLogicImpl implements DishesBusinsessLogicInt {

	@Autowired
	private RestClientInt wsClient;
	
	@Autowired
	private DishesServiceProperties prpDishServ;
	
	
	@Override
	public boolean insertDish(ApiDish dish) {
		URL url;
		try {
			url = new URL(prpDishServ.getProtocol(), prpDishServ.getHost(), prpDishServ.getInsertDish());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			//TODO manage the error in a better way
			return false;
		}
		ClientResponse resp = wsClient.performPostRequest(Optional.of(PricesConstants.TOKEN_DISHES_REGISTRATION_ID), url, Optional.empty(), Optional.empty(), Optional.of(dish));
		return resp.statusCode() == HttpStatus.CREATED;
	}

	@Override
	public boolean updateDish(ApiDish dish) {
		URL url;
		try {
			url = new URL(prpDishServ.getProtocol(), prpDishServ.getHost(), prpDishServ.getUpdateDish());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			//TODO manage the error in a better way
			return false;
		}
		ClientResponse resp = wsClient.performPutRequest(Optional.of(PricesConstants.TOKEN_DISHES_REGISTRATION_ID), url, Optional.empty(), Optional.empty(), Optional.of(dish));
		return resp.statusCode() == HttpStatus.NO_CONTENT;
	}

	@Override
	public boolean deleteDish(String name) {
		URL url;
		try {
			url = new URL(prpDishServ.getProtocol(), prpDishServ.getHost(), prpDishServ.getDeleteDish(name));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			//TODO manage the error in a better way
			return false;
		}
		ClientResponse resp = wsClient.performDeleteRequest(Optional.of(PricesConstants.TOKEN_DISHES_REGISTRATION_ID), url, Optional.empty(), Optional.empty(), Optional.empty());
		return resp.statusCode() == HttpStatus.NO_CONTENT;
	}

	@Override
	public ApiDish findDish(String name) {
		URL url;
		try {
			url = new URL(prpDishServ.getProtocol(), prpDishServ.getHost(), prpDishServ.getFindDishByName(name));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			//TODO manage the error in a better way
			return null;
		}
		ClientResponse resp = wsClient.performGetRequest(Optional.of(PricesConstants.TOKEN_DISHES_REGISTRATION_ID), url, Optional.empty(), Optional.empty());
		if(resp.statusCode() == HttpStatus.OK) {
			return wsClient.getBodyFromResponse(resp, ApiDish.class);
		}
		return null;
	}

	@Override
	public List<ApiDish> findAllDish() {
		URL url;
		try {
			url = new URL(prpDishServ.getProtocol(), prpDishServ.getHost(), prpDishServ.getFindAllDishes());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			//TODO manage the error in a better way
			return null;
		}
		ClientResponse resp = wsClient.performGetRequest(Optional.of(PricesConstants.TOKEN_DISHES_REGISTRATION_ID), url, Optional.empty(), Optional.empty());
		if(resp.statusCode() == HttpStatus.OK) {
			ApiDishes dishes = wsClient.getBodyFromResponse(resp, ApiDishes.class);
			return dishes.getDishes();
		}
		return new ArrayList<>();
	}



}
