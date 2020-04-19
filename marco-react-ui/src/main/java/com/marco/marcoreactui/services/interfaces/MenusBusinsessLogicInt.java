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
	public List<ApiMenu> getAllMenus();

	public boolean insertMenu(ApiMenu newMenu);

	public boolean deleteMenu(String menuName);
}
