package com.marco.marcoreactui.dto.ingredients;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the Dish recipe definition exchanged via the API
 * 
 * @author msolina
 *
 */
public class ApiDishRecipe implements Serializable {
	private static final long serialVersionUID = 1L;
	private String dishName;
	private Set<String> ingredients;

	public ApiDishRecipe() {
	}

	public ApiDishRecipe(String dishName, Set<String> ingredients) {
		this.dishName = dishName;
		this.ingredients = ingredients;
	}

	public boolean addIngredient(String ingredient) {
		if (this.ingredients == null) {
			this.ingredients = new HashSet<>();
		}
		return this.ingredients.add(ingredient);
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public Set<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<String> ingredients) {
		this.ingredients = ingredients;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
