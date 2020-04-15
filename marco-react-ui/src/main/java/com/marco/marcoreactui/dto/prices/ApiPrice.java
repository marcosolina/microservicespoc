package com.marco.marcoreactui.dto.prices;

import java.io.Serializable;

public class ApiPrice implements Serializable {
	private static final long serialVersionUID = 1L;
	private String dishName;
	private Integer price;

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
