package com.marco.marcoreactui.services.implementations;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;

import com.marco.marcoreactui.config.properties.PricesServiceProperties;
import com.marco.marcoreactui.dto.prices.ApiPrice;
import com.marco.marcoreactui.dto.prices.ApiPrices;
import com.marco.marcoreactui.services.interfaces.PricesBusinsessLogicInt;
import com.marco.marcoreactui.services.interfaces.RestClientInt;
import com.marco.marcoreactui.utils.PricesConstants;

public class PricesBusinsessLogicImpl implements PricesBusinsessLogicInt {

	@Autowired
	private RestClientInt wsClient;
	
	@Autowired
	private PricesServiceProperties prpPriceServ;
	
	
	@Override
	public boolean insertPrice(ApiPrice price) {
		URL url;
		try {
			url = new URL(prpPriceServ.getProtocol(), prpPriceServ.getHost(), prpPriceServ.getInsertPrice());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			//TODO manage the error in a better way
			return false;
		}
		ClientResponse resp = wsClient.performPostRequest(Optional.of(PricesConstants.TOKEN_DISHES_REGISTRATION_ID), url, Optional.empty(), Optional.empty(), Optional.of(price));
		return resp.statusCode() == HttpStatus.CREATED;
	}

	@Override
	public boolean updatePrice(ApiPrice price) {
		URL url;
		try {
			url = new URL(prpPriceServ.getProtocol(), prpPriceServ.getHost(), prpPriceServ.getUpdatePrice());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			//TODO manage the error in a better way
			return false;
		}
		ClientResponse resp = wsClient.performPutRequest(Optional.of(PricesConstants.TOKEN_DISHES_REGISTRATION_ID), url, Optional.empty(), Optional.empty(), Optional.of(price));
		return resp.statusCode() == HttpStatus.NO_CONTENT;
	}

	@Override
	public boolean deletePrice(String name) {
		URL url;
		try {
			url = new URL(prpPriceServ.getProtocol(), prpPriceServ.getHost(), prpPriceServ.getDeletePrice(name));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			//TODO manage the error in a better way
			return false;
		}
		ClientResponse resp = wsClient.performDeleteRequest(Optional.of(PricesConstants.TOKEN_DISHES_REGISTRATION_ID), url, Optional.empty(), Optional.empty(), Optional.empty());
		return resp.statusCode() == HttpStatus.NO_CONTENT;
	}

	@Override
	public ApiPrice findPrice(String name) {
		URL url;
		try {
			url = new URL(prpPriceServ.getProtocol(), prpPriceServ.getHost(), prpPriceServ.getFindPriceByName(name));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			//TODO manage the error in a better way
			return null;
		}
		ClientResponse resp = wsClient.performGetRequest(Optional.of(PricesConstants.TOKEN_DISHES_REGISTRATION_ID), url, Optional.empty(), Optional.empty());
		if(resp.statusCode() == HttpStatus.OK) {
			return wsClient.getBodyFromResponse(resp, ApiPrice.class);
		}
		return null;
	}

	@Override
	public List<ApiPrice> findAllPrice() {
		URL url;
		try {
			url = new URL(prpPriceServ.getProtocol(), prpPriceServ.getHost(), prpPriceServ.getFindAllPrices());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			//TODO manage the error in a better way
			return null;
		}
		ClientResponse resp = wsClient.performGetRequest(Optional.of(PricesConstants.TOKEN_DISHES_REGISTRATION_ID), url, Optional.empty(), Optional.empty());
		if(resp.statusCode() == HttpStatus.OK) {
			ApiPrices prices = wsClient.getBodyFromResponse(resp, ApiPrices.class);
			return prices.getPrices();
		}
		return new ArrayList<>();
	}



}
