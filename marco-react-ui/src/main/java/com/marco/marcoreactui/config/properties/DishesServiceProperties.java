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
@ConfigurationProperties("rest.request.service.dishes")
public class DishesServiceProperties {
	private String host;
	private String protocol;
	private String findDishByName;
	private String findAllDishes;
	private String deleteDish;
	private String insertDish;
	private String updateDish;

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

	public String getFindDishByName(String dishName) {
		return findDishByName + "/" + dishName;
	}

	public void setFindDishByName(String findDishByName) {
		this.findDishByName = findDishByName;
	}

	public String getFindAllDishes() {
		return findAllDishes;
	}

	public void setFindAllDishes(String findAllDishes) {
		this.findAllDishes = findAllDishes;
	}

	public String getDeleteDish(String dishName) {
		return deleteDish + "/" + dishName;
	}

	public void setDeleteDish(String deleteDish) {
		this.deleteDish = deleteDish;
	}

	public String getInsertDish() {
		return insertDish;
	}

	public void setInsertDish(String insertDish) {
		this.insertDish = insertDish;
	}

	public String getUpdateDish() {
		return updateDish;
	}

	public void setUpdateDish(String updateDish) {
		this.updateDish = updateDish;
	}

}
