package com.marco.ingridientsservice.dto;

import java.io.Serializable;
import java.util.Set;

public class ApiDishRecipe implements Serializable {
    private static final long serialVersionUID = 1L;
    private String dishName;
    private boolean available;
    private Set<String> ingredients;

    public ApiDishRecipe() {
    }

    public ApiDishRecipe(String dishName, Set<String> ingredients) {
        this.dishName = dishName;
        this.ingredients = ingredients;
    }

    public boolean addIngredient(String ingredient) {
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
