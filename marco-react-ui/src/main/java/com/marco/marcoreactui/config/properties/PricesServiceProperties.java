package com.marco.marcoreactui.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Standard Spring properties class
 * 
 * @author msolina
 *
 */
@Configuration
@ConfigurationProperties("rest.request.service.ces")
public class PricesServiceProperties {
	private String host;
	private String protocol;
	private String findPriceByName;
	private String findAllPrices;
	private String deletePrice;
	private String insertPrice;
	private String updatePrice;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getFindPriceByName(String dishName) {
		return findPriceByName + "/" + dishName;
	}

	public void setFindPriceByName(String findPriceByName) {
		this.findPriceByName = findPriceByName;
	}

	public String getFindAllPrices() {
		return findAllPrices;
	}

	public void setFindAllPrices(String findAllPrices) {
		this.findAllPrices = findAllPrices;
	}

	public String getDeletePrice(String dishName) {
		return deletePrice + "/" + dishName;
	}

	public void setDeletePrice(String deletePrice) {
		this.deletePrice = deletePrice;
	}

	public String getInsertPrice() {
		return insertPrice;
	}

	public void setInsertPrice(String insertPrice) {
		this.insertPrice = insertPrice;
	}

	public String getUpdatePrice() {
		return updatePrice;
	}

	public void setUpdatePrice(String updatePrice) {
		this.updatePrice = updatePrice;
	}

}
