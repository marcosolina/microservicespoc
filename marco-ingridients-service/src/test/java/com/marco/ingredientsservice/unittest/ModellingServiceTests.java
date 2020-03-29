package com.marco.ingredientsservice.unittest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.marco.ingridientsservice.dto.ApiDishRecipe;
import com.marco.ingridientsservice.dto.ApiIngredient;
import com.marco.ingridientsservice.dto.ApiRecipes;
import com.marco.ingridientsservice.model.Ingredient;
import com.marco.ingridientsservice.model.IngredientPk;
import com.marco.ingridientsservice.services.implementations.ModellingServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ModellingServiceTests {

    private ModellingServiceImpl service = new ModellingServiceImpl();

    private Ingredient iOne = new Ingredient();
    private Ingredient iTwo = new Ingredient();
    private ApiDishRecipe recipe = new ApiDishRecipe();

    {
        iOne.setId(new IngredientPk("ONE", "IONE"));
        iTwo.setId(new IngredientPk("ONE", "ITWO"));
        recipe.setDishName("ONE");
        recipe.addIngredient("IONE");
    }

    private List<Ingredient> list = Arrays.asList(iOne, iTwo);

    @Test
    public void fromDishToApiDish() {
        ApiDishRecipe r = service.fromDishToApiDish(list);

        assertThat(r).isNotNull();
        assertThat(r.getDishName()).isEqualTo("ONE");
        assertFalse(r.getIngredients().isEmpty());
    }

    @Test
    public void fromDishListToApiRecipes() {
        ApiRecipes r = service.fromDishListToApiRecipes(list);
        assertThat(r).isNotNull();
        assertFalse(r.getRecipes().isEmpty());
    }

    @Test
    public void fromApiDishToDish() {
        List<Ingredient> l = service.fromApiDishToDish(recipe);
        assertThat(l).isNotNull();
        assertFalse(l.isEmpty());
    }

    @Test
    public void fromApiIngredientToIngredient() {
        assertThat(service.fromApiIngredientToIngredient(new ApiIngredient("ONE", "IONE"))).isNotNull();
    }

}
