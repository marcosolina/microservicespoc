package com.marco.menuservice.services.interfaces;

import java.util.List;

import com.marco.menuservice.errors.MarcoException;
import com.marco.menuservice.model.sql.Menu;

/**
 * This interface performs the business logic
 * 
 * @author msolina
 *
 */
public interface BusinsessLogicInt {
    /**
     * It inserts a {@link Menu} in the database. It throws a
     * {@link MarcoException} if the entry already exists
     * 
     * @param menu
     * @return
     * @throws MarcoException
     */
    public boolean insertMenu(Menu menu) throws MarcoException;

    /**
     * It deletes a {@link Menu} in the database. It throws a
     * {@link MarcoException} if the entry do not exists
     * 
     * @param menu
     * @return
     * @throws MarcoException
     */
    public boolean deleteMenu(Menu menu) throws MarcoException;

    /**
     * It returns a list of {@link Menu} matching the provided menu name
     * 
     * @param menuName
     * @return
     * @throws MarcoException
     */
    public List<Menu> findDishesForMenu(String menuName) throws MarcoException;

    /**
     * It deletes all the {@link Menu} in the database with the provided Menu
     * Name. It throws a {@link MarcoException} there are no entries
     * 
     * @param menu
     * @return
     * @throws MarcoException
     */
    public boolean deleteAllDishesForMenu(String menuName) throws MarcoException;

    /**
     * It returns a list of all the available {@link Menu}
     * 
     * @return
     */
    public List<Menu> findAllMenus();

    /**
     * It checks if the dish is defined in the Dishes service. It throws a
     * {@link MarcoException} if it is not able to contact the Dishes service
     * 
     * @param dishName
     * @return
     * @throws MarcoException
     */
    public boolean checkIfDishExistInDishesService(String dishName) throws MarcoException;
}
