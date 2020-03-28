package com.marco.ingredientsservice.unittest;

import java.util.List;

import com.marco.ingridientsservice.dto.ApiDishRecipe;
import com.marco.ingridientsservice.dto.ApiIngredient;
import com.marco.ingridientsservice.dto.ApiRecipes;
import com.marco.ingridientsservice.model.Ingredient;

/**
 * Simple service to help the controller to retrieve the data from the data
 * source
 * 
 * @author msolina
 *
 */
public interface ModellingServiceInt {

    /**
     * It converts a DB Dish {@link Dish} into an API Dish {@link ApiDish}
     * 
     * @param dish
     * @return
     */
    public ApiDishRecipe fromDishToApiDish(List<Ingredient> ingredients);
    
    public ApiRecipes fromDishListToApiRecipes(List<Ingredient> ingredients);

    /**
     * It converts an API Dish {@link ApiDish} into a DB Dish {@link Dish}
     * 
     * @param apiDish
     * @return
     */
    public List<Ingredient> fromApiDishToDish(ApiDishRecipe recipe);
    
    /**
     * It converts the API ingredient {@link }
     * @param apiIngredient
     * @return
     */
    public Ingredient fromApiIngredientToIngredient(ApiIngredient apiIngredient);
    
}
