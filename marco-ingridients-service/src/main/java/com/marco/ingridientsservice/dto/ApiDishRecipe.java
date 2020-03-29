package com.marco.ingridientsservice.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This class represents the Dish recipe definition exchanged via the API
 * 
 * @author msolina
 *
 */
@ApiModel(value = "Dish Recepy")
public class ApiDishRecipe implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "Name of the dish")
    private String dishName;
    @ApiModelProperty(value = "It indicates if this dish is still available in the Dishes service or not")
    private boolean available;
    @ApiModelProperty(value = "Set of ingredients (unique)", dataType = "Set")
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
