package com.marco.menuservice.services.implementations;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.marco.menuservice.dto.ApiMenu;
import com.marco.menuservice.dto.ApiMenus;
import com.marco.menuservice.model.sql.Menu;
import com.marco.menuservice.model.sql.MenuPk;
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

    @Override
    public ApiMenus fromMenuListToApiMenus(List<Menu> menuList) {
        Map<String, Set<String>> map = new HashMap<>();

        menuList.stream().forEach(menu -> {
            map.putIfAbsent(menu.getId().getMenuName(), new HashSet<>());
            map.compute(menu.getId().getMenuName(), (k, v) -> {
                v.add(menu.getId().getDishName());
                return v;
            });
        });

        ApiMenus menus = new ApiMenus();
        map.forEach((k, v) -> menus.addMenu(new ApiMenu(k, v)));

        return menus;
    }

}
