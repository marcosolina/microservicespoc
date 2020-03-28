package com.marco.menuservice.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import com.marco.menuservice.dto.ApiMenu;
import com.marco.menuservice.model.Menu;
import com.marco.menuservice.model.MenuPk;
import com.marco.menuservice.services.interfaces.ModellingServiceInt;

public class ModellingServiceImpl implements ModellingServiceInt {

    @Override
    public List<Menu> fromApiMenuToMenuList(ApiMenu apiMenu) {
        return apiMenu.getDishes().stream().map(dishName -> {
            Menu m = new Menu();
            m.setId(new MenuPk(apiMenu.getMenuName(), dishName));
            return m;
        }).collect(Collectors.toList());
    }

    @Override
    public ApiMenu fromMenuListToApiMenu(List<Menu> menuList) {
        ApiMenu am = new ApiMenu();
        menuList.stream().forEach(menu -> {
            am.setMenuName(menu.getId().getMenuName());
            am.addDishName(menu.getId().getDishName());
        });

        return am;
    }

}
