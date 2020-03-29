package com.marco.ingridientsservice.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This class represents the definition of a single ingredient
 * 
 * @author msolina
 *
 */
@ApiModel(value = "Ingridient representation", description = "It represents a single ingredient for the specific dish")
public class ApiIngredient implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "Dish name")
    private String dishName;

    @ApiModelProperty(value = "Ingredient required for this dish")
    private String ingredientName;

    public ApiIngredient() {

    }

    public ApiIngredient(String dishName, String ingredientName) {
        this.dishName = dishName;
        this.ingredientName = ingredientName;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dishName == null) ? 0 : dishName.hashCode());
        result = prime * result + ((ingredientName == null) ? 0 : ingredientName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ApiIngredient other = (ApiIngredient) obj;
        if (dishName == null) {
            if (other.dishName != null)
                return false;
        } else if (!dishName.equals(other.dishName))
            return false;
        if (ingredientName == null) {
            if (other.ingredientName != null)
                return false;
        } else if (!ingredientName.equals(other.ingredientName))
            return false;
        return true;
    }

}
