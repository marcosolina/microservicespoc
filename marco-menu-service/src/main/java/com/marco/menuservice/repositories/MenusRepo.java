package com.marco.menuservice.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.marco.menuservice.model.Menu;
import com.marco.menuservice.model.MenuPk;

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
public interface MenusRepo extends CrudRepository<Menu, MenuPk> {
    /**
     * It returns a list {@link Menu} that matches provided menu name
     * 
     * @param menuName
     * @return
     */
    List<Menu> findByIdMenuName(String menuName);

    /**
     * It deletes all the {@link Menu} that matches provided menu name
     * 
     * @param menuName
     */
    void deleteByIdMenuName(String menuName);
}
