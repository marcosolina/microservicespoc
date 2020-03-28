package com.marco.menuservice.services.interfaces;

import java.util.List;

import com.marco.menuservice.dto.ApiMenu;
import com.marco.menuservice.model.Menu;

/**
 * Simple service to help the controller to retrieve the data from the data
 * source
 * 
 * @author msolina
 *
 */
public interface ModellingServiceInt {

    public List<Menu> fromApiMenuToMenuList(ApiMenu apiMenu);

    public ApiMenu fromMenuListToApiMenu(List<Menu> menuList);
}
