package com.marco.ingridientsservice.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.marco.ingridientsservice.model.Ingredient;
import com.marco.ingridientsservice.model.IngredientPk;

/**
 * This interface provides basic CRUD functions. It uses the "Spring" magic :)
 * 
 * @see
 *      <ul>
 *      <li><a href=
 *      "https://docs.spring.io/spring-data/data-jpa/docs/2.2.4.RELEASE/reference/html/#repositories.definition">Defining
 *      Repository Interfaces</a></li>
 *      <li><a href=
 *      "https://docs.spring.io/spring-data/data-jpa/docs/2.2.4.RELEASE/reference/html/#jpa.query-methods.query-creation">Query
 *      Creation</a></li>
 *      <li><a href=
 *      "https://docs.spring.io/spring-data/data-jpa/docs/2.2.4.RELEASE/reference/html/#jpa.query-methods.at-query">Using
 *      Query</a></li>
 *      </ul>
 * 
 * @author msolina
 *
 */
public interface IngredientsRepo extends CrudRepository<Ingredient, IngredientPk> {
    /**
     * It returns the list of ingredients for the specific dish
     * 
     * @param dishName
     * @return
     */
    List<Ingredient> findByIdDishName(String dishName);

    /**
     * It deletes all the ingredients for the specific dish
     * 
     * @param dishName
     */
    void deleteByIdDishName(String dishName);
}
