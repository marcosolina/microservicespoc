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

	/**
	 * It returns a list of all the recipes
	 * 
	 * @return
	 */
	public List<ApiDishRecipe> getAllRecipes();

	/**
	 * It insert the definition of a new Recipe
	 * 
	 * @param recipe
	 * @return
	 */
	public boolean insertRecipe(ApiDishRecipe recipe);

	/**
	 * It deletes the recipe of the specified dish
	 * 
	 * @param dishName
	 * @return
	 */
	public boolean deleteRecipe(String dishName);
}
