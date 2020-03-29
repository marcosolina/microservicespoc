package com.marco.ingredientsservice.unittest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.marco.ingridientsservice.controllers.IngridientsController;
import com.marco.ingridientsservice.dto.ApiDishRecipe;
import com.marco.ingridientsservice.dto.ApiRecipes;
import com.marco.ingridientsservice.errors.MarcoException;
import com.marco.ingridientsservice.model.Ingredient;
import com.marco.ingridientsservice.model.IngredientPk;
import com.marco.ingridientsservice.services.interfaces.BusinsessLogicInt;
import com.marco.ingridientsservice.services.interfaces.ModellingServiceInt;

@ExtendWith(MockitoExtension.class)
public class IngridientsControllerTests {

    @InjectMocks
    private IngridientsController controller = new IngridientsController();

    @Mock
    private BusinsessLogicInt bli;

    @Mock
    private ModellingServiceInt msi;

    private ApiDishRecipe recipe = new ApiDishRecipe();
    private ApiRecipes recipes = new ApiRecipes();
    private Ingredient iOne = new Ingredient();
    private Ingredient iTwo = new Ingredient();
    private String dishName = "DISH";

    {
        iOne.setId(new IngredientPk(dishName, "ONE"));
        iTwo.setId(new IngredientPk(dishName, "TWO"));
        recipe.setDishName(dishName);
        recipe.addIngredient("ONE");
        recipe.addIngredient("TWO");
        recipes.addRecipe(recipe);
    }

    @Test
    public void insertIngredient() throws MarcoException {
        when(bli.checkIfDishExistInDishesService(ArgumentMatchers.anyString())).thenReturn(true);
        when(msi.fromApiIngredientToIngredient(ArgumentMatchers.any())).thenReturn(iOne);
        when(bli.insertIngredient(ArgumentMatchers.any())).thenReturn(true);
        
        ResponseEntity<Void> resp = controller.insertIngredient(recipe);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void getAllRecipies() throws MarcoException {
        when(bli.findAllIngredients()).thenReturn(Arrays.asList(iOne, iTwo));
        when(msi.fromDishListToApiRecipes(ArgumentMatchers.any())).thenReturn(recipes);
        
        ResponseEntity<ApiRecipes> resp = controller.getAllRecipies();
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void getDishRecipy() throws MarcoException {
        when(bli.findIngredients(dishName)).thenReturn(Arrays.asList(iOne, iTwo));
        when(msi.fromDishToApiDish(ArgumentMatchers.any())).thenReturn(recipe);
        
        ResponseEntity<ApiDishRecipe> resp = controller.getDishRecipy(dishName);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void deleteRecepy() throws MarcoException {
        when(bli.deleteIngredients(dishName)).thenReturn(true);
        
        ResponseEntity<Void> resp = controller.deleteRecepy(dishName);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}