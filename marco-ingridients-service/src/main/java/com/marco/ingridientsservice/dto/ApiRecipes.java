package com.marco.ingridientsservice.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This is a wrapper class to wrap the list of Recipes to exchange via the API
 * 
 * @author msolina
 *
 */
@ApiModel(value = "Recipes wrapper", description = "It contains the list of recipes")
public class ApiRecipes implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "List of recipes")
    private List<ApiDishRecipe> recipes;

    public ApiRecipes() {
    }

    public ApiRecipes(List<ApiDishRecipe> recipes) {
        this.recipes = recipes;
    }

    public boolean addRecipe(ApiDishRecipe recipe) {
        if (this.recipes == null) {
            this.recipes = new ArrayList<>();
        }
        return this.recipes.add(recipe);
    }

    public List<ApiDishRecipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<ApiDishRecipe> recipes) {
        this.recipes = recipes;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
