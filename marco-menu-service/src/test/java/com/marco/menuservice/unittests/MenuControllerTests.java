package com.marco.menuservice.unittests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.marco.menuservice.controllers.MenuController;
import com.marco.menuservice.dto.ApiMenu;
import com.marco.menuservice.dto.ApiMenus;
import com.marco.menuservice.errors.MarcoException;
import com.marco.menuservice.model.sql.Menu;
import com.marco.menuservice.model.sql.MenuPk;
import com.marco.menuservice.services.interfaces.BusinsessLogicInt;
import com.marco.menuservice.services.interfaces.ModellingServiceInt;

@ExtendWith(MockitoExtension.class)
public class MenuControllerTests {

    @InjectMocks
    private MenuController controller = new MenuController();

    @Mock
    private BusinsessLogicInt bli;

    @Mock
    private ModellingServiceInt msi;

    private String dishName = "DISH";
    private String dishName2 = "DISH2";
    private String menuName = "MENU";
    private Menu menu1 = new Menu();
    private Menu menu2 = new Menu();
    private ApiMenu apiMenu = new ApiMenu();
    private ApiMenus apiMenus = new ApiMenus();
    {
        menu1.setId(new MenuPk(menuName, dishName));
        menu2.setId(new MenuPk(menuName, dishName2));
        apiMenu.setMenuName(menuName);
        apiMenu.addDishName(dishName);
        apiMenu.addDishName(dishName2);
        apiMenus.addMenu(apiMenu);
    }

    @Test
    public void insertMenu() throws MarcoException {
        when(msi.fromApiMenuToMenuList(ArgumentMatchers.any())).thenReturn(Arrays.asList(menu1, menu2));
        when(bli.insertMenu(ArgumentMatchers.any())).thenReturn(true);
        when(bli.checkIfDishExistInDishesService(ArgumentMatchers.anyString())).thenReturn(true);

        ResponseEntity<Void> resp = controller.insertMenu(apiMenu);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void getAllMenus() {
        when(bli.findAllMenus()).thenReturn(Arrays.asList(menu1, menu2));
        when(msi.fromMenuListToApiMenus(ArgumentMatchers.any())).thenReturn(apiMenus);

        ResponseEntity<ApiMenus> resp = controller.getAllMenus();
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertFalse(resp.getBody().getMenus().isEmpty());
    }

    @Test
    public void getMenu() throws MarcoException {
        when(bli.findDishesForMenu(menuName)).thenReturn(Arrays.asList(menu1, menu2));
        when(msi.fromMenuListToApiMenu(ArgumentMatchers.any())).thenReturn(apiMenu);

        ResponseEntity<ApiMenu> resp = controller.getMenu(menuName);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertFalse(resp.getBody().getDishes().isEmpty());
    }

    @Test
    public void deleteMenu() throws MarcoException {
        when(bli.deleteAllDishesForMenu(menuName)).thenReturn(true);

        ResponseEntity<Void> resp = controller.deleteMenu(menuName);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}