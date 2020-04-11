package com.marco.ingridientsservice.services.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.marco.ingridientsservice.dto.ApiDishRecipe;
import com.marco.ingridientsservice.dto.ApiIngredient;
import com.marco.ingridientsservice.dto.ApiRecipes;
import com.marco.ingridientsservice.model.Ingredient;
import com.marco.ingridientsservice.model.IngredientPk;
import com.marco.ingridientsservice.services.interfaces.ModellingServiceInt;

public class ModellingServiceImpl implements ModellingServiceInt {

    @Override
    public ApiDishRecipe fromDishToApiDish(List<Ingredient> ingredients) {
        ApiDishRecipe recipe = new ApiDishRecipe();
        recipe.setIngredients(new HashSet<>());

        ingredients.stream().forEach(ingredient -> {
            recipe.setDishName(ingredient.getId().getDishName());
            recipe.addIngredient(ingredient.getId().getIngredientName());
        });

        return recipe;
    }

    @Override
    public List<Ingredient> fromApiDishToDish(ApiDishRecipe recipe) {
        List<Ingredient> list = new ArrayList<>();

        recipe.getIngredients().stream().forEach(ingName -> list.add(new Ingredient().setId(new IngredientPk(recipe.getDishName(), ingName))));

        return list;
    }

    @Override
    public Ingredient fromApiIngredientToIngredient(ApiIngredient apiIngredient) {
        if (apiIngredient == null) {
            return null;
        }

        Ingredient i = new Ingredient();
        i.setId(new IngredientPk(apiIngredient.getDishName(), apiIngredient.getIngredientName()));
        return i;
    }

    @Override
    public ApiRecipes fromDishListToApiRecipes(List<Ingredient> ingredients) {
        Map<String, Set<String>> map = new HashMap<>();

        ingredients.stream().forEach(ingredient -> {
            map.putIfAbsent(ingredient.getId().getDishName(), new HashSet<>());
            map.compute(ingredient.getId().getDishName(), (k, v) -> {
                v.add(ingredient.getId().getIngredientName());
                return v;
            });
        });

        ApiRecipes recipes = new ApiRecipes();
        map.forEach((k, v) -> recipes.addRecipe(new ApiDishRecipe(k, v)));

        return recipes != null ? recipes : new ApiRecipes();
    }

}
