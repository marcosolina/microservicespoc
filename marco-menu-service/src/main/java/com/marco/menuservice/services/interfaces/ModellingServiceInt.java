package com.marco.menuservice.services.interfaces;

import java.util.List;

import com.marco.menuservice.dto.ApiMenu;
import com.marco.menuservice.dto.ApiMenus;
import com.marco.menuservice.model.sql.Menu;

/**
 * Simple service to help the controller to retrieve the data from the data
 * source
 * 
 * @author msolina
 *
 */
public interface ModellingServiceInt {

    /**
     * It converts the {@link ApiMenu} into a list of {@link Menu}
     * 
     * @param apiMenu
     * @return
     */
    public List<Menu> fromApiMenuToMenuList(ApiMenu apiMenu);

    /**
     * It converts a list of {@link Menu} into an {@link ApiMenu}
     * 
     * @param menuList
     * @return
     */
    public ApiMenu fromMenuListToApiMenu(List<Menu> menuList);

    /**
     * It converts a list of {@link Menu} into an {@link ApiMenus}
     * 
     * @param menuList
     * @return
     */
    public ApiMenus fromMenuListToApiMenus(List<Menu> menuList);
}
