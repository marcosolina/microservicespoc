package com.marco.pricesservice.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.marco.pricesservice.model.Price;

/**
 * This interface provides basic CRUD functions. 
 * It uses the "Spring" magic :)
 * 
 * @see 
 * <ul>
 * <li><a href="https://docs.spring.io/spring-data/data-jpa/docs/2.2.4.RELEASE/reference/html/#repositories.definition">Defining Repository Interfaces</a></li>
 * <li><a href="https://docs.spring.io/spring-data/data-jpa/docs/2.2.4.RELEASE/reference/html/#jpa.query-methods.query-creation">Query Creation</a></li>
 * <li><a href="https://docs.spring.io/spring-data/data-jpa/docs/2.2.4.RELEASE/reference/html/#jpa.query-methods.at-query">Using Query</a></li>
 * </ul>
 * 
 * @author msolina
 *
 */
public interface PriceRepository extends CrudRepository<Price, String>{

	/**
	 * Try to retrieve the dish with the provided name from the database
	 * @param name
	 * @return An optional
	 */
	Optional<Price> findByDishName(String name);
	
	/**
	 * Try to delete the dish with the provided name
	 * @param name
	 */
	void deleteByDishName(String name);
}
