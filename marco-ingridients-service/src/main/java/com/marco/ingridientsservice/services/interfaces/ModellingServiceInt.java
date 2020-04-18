package com.marco.ingridientsservice.services.interfaces;

import java.util.List;

import com.marco.ingridientsservice.dto.ApiDishRecipe;
import com.marco.ingridientsservice.dto.ApiIngredient;
import com.marco.ingridientsservice.dto.ApiRecipes;
import com.marco.ingridientsservice.model.sql.Ingredient;

/**
 * Simple service to helps the controller to retrieve the data from the data
 * source
 * 
 * @author msolina
 *
 */
public interface ModellingServiceInt {

    /**
     * It converts a DB List of {@link Ingredient} into an API ApiDishRecipe
     * {@link ApiDishRecipe}
     * 
     * @param List
     *            of ingredients
     * @return
     */
    public ApiDishRecipe fromDishToApiDish(List<Ingredient> ingredients);

    /**
     * It converts a DB List of {@link Ingredient} into an API ApiRecipes
     * {@link ApiRecipes}
     * 
     * @param ingredients
     * @return
     */
    public ApiRecipes fromDishListToApiRecipes(List<Ingredient> ingredients);

    /**
     * It converts an API recipe {@link ApiDishRecipe} into a DB list of {@link Ingredient}
     * 
     * @param recipe
     * @return List<Ingredient>
     */
    public List<Ingredient> fromApiDishToDish(ApiDishRecipe recipe);

    /**
     * It converts the API {@link ApiIngredient} into a DB {@link Ingredient}
     * 
     * @param apiIngredient
     * @return
     */
    public Ingredient fromApiIngredientToIngredient(ApiIngredient apiIngredient);

}
