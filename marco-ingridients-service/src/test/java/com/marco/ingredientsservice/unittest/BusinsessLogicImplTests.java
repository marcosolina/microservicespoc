package com.marco.ingredientsservice.unittest;

import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.marco.ingridientsservice.errors.MarcoException;
import com.marco.ingridientsservice.model.Ingredient;

@ExtendWith(MockitoExtension.class)
public interface BusinsessLogicImplTests {
    public boolean insertIngredient(Ingredient ingridient) throws MarcoException;

    public boolean deleteIngredient(Ingredient ingridient) throws MarcoException;

    public List<Ingredient> findIngredients(String dishName) throws MarcoException;
    
    public boolean deleteIngredients(String dishName) throws MarcoException;

    public List<Ingredient> findAllIngredients();

    public boolean checkIfDishExistInDishesService(String dishName) throws MarcoException;
}
