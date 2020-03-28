package com.marco.menuservice.services.interfaces;

import java.util.List;

import com.marco.menuservice.errors.MarcoException;
import com.marco.menuservice.model.Menu;

/**
 * This interface allow the CRUD operation in the DATABASE
 * 
 * @author msolina
 *
 */
public interface BusinsessLogicInt {
    public boolean insertMenu(Menu menu) throws MarcoException;


    public boolean deleteMenu(Menu menu) throws MarcoException;

    public List<Menu> findDishesForMenu(String menuName) throws MarcoException;
    
    public boolean deleteAllDishesForMenu(String menuName) throws MarcoException;

    public List<Menu> findAllMenus();

    public boolean checkIfDishExistInDishesService(String dishName) throws MarcoException;
}
