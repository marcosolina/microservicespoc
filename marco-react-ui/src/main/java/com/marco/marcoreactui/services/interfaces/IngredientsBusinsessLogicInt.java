package com.marco.marcoreactui.services.interfaces;

import java.util.List;

import com.marco.marcoreactui.dto.ingredients.ApiDishRecipe;

/**
 * This interface defines the business logic for the Ingredients operations
 * 
 * @author marco
 *
 */
public interface IngredientsBusinsessLogicInt {

	public List<ApiDishRecipe> getAllRecipes();

	public boolean insertRecipe(ApiDishRecipe recipe);

	public boolean deleteRecipe(String dishName);
}
