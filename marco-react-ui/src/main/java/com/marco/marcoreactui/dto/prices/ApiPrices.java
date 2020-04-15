package com.marco.marcoreactui.dto.prices;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Pojo used in the Rest API to return a list of {@link ApiPrice}
 * 
 * @author msolina
 *
 */
public class ApiPrices implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<ApiPrice> prices = new ArrayList<>();

	public boolean addApiPrice(ApiPrice apiPrice) {
		return prices.add(apiPrice);
	}

	public List<ApiPrice> getPrices() {
		return prices;
	}

	public void setPrices(List<ApiPrice> prices) {
		this.prices = prices;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
