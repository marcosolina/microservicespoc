package com.marco.menuservice.unittests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.marco.menuservice.dto.ApiMenu;
import com.marco.menuservice.dto.ApiMenus;
import com.marco.menuservice.model.Menu;
import com.marco.menuservice.model.MenuPk;
import com.marco.menuservice.services.implementations.ModellingServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ModellingServiceTests {

    private ModellingServiceImpl service = new ModellingServiceImpl();

    private String dishName = "DISH";
    private String dishName2 = "DISH2";
    private String menuName = "MENU";
    private Menu menu1 = new Menu();
    private Menu menu2 = new Menu();
    private ApiMenu apiMenu = new ApiMenu();
    {
        menu1.setId(new MenuPk(menuName, dishName));
        menu2.setId(new MenuPk(menuName, dishName2));
        apiMenu.setMenuName(menuName);
        apiMenu.addDishName(dishName);
        apiMenu.addDishName(dishName2);
    }

    @Test
    public void fromApiMenuToMenuList() {
        List<Menu> list = service.fromApiMenuToMenuList(apiMenu);
        assertFalse(list.isEmpty());
    }

    @Test
    public void fromMenuListToApiMenu() {
        ApiMenu m = service.fromMenuListToApiMenu(Arrays.asList(menu1, menu2));
        assertThat(m.getMenuName()).isEqualTo(menuName);
        assertFalse(m.getDishes().isEmpty());
    }

    @Test
    public void fromMenuListToApiMenus() {
        ApiMenus m = service.fromMenuListToApiMenus(Arrays.asList(menu1, menu2));
        assertFalse(m.getMenus().isEmpty());
    }

}
