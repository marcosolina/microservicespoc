package com.marco.marcoreactui.services.interfaces;

import java.util.List;

import com.marco.marcoreactui.dto.menu.ApiMenu;

/**
 * This interface defines the business logic for the Menu operations
 * 
 * @author marco
 *
 */
public interface MenusBusinsessLogicInt {

	/**
	 * It returns a list of all the available menus
	 * 
	 * @return
	 */
	public List<ApiMenu> getAllMenus();

	/**
	 * It inserts a new menu
	 * 
	 * @param newMenu
	 * @return
	 */
	public boolean insertMenu(ApiMenu newMenu);

	/**
	 * It deletes the menu with the provided name
	 * 
	 * @param menuName
	 * @return
	 */
	public boolean deleteMenu(String menuName);
}
