package com.marco.pricesservice.repositories.sql;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.marco.pricesservice.model.sql.Price;

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
public interface PriceRepository extends CrudRepository<Price, String> {

    /**
     * It returns the price for the provided dish name
     * 
     * @param name
     * @return An optional
     */
    Optional<Price> findByDishName(String name);

    /**
     * It deletes the price for the provided dish name
     * 
     * @param name
     */
    void deleteByDishName(String name);
}
