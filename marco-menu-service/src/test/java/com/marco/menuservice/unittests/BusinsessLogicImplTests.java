package com.marco.menuservice.unittests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;

import com.marco.menuservice.config.properties.DishesServiceProperties;
import com.marco.menuservice.errors.MarcoException;
import com.marco.menuservice.model.Menu;
import com.marco.menuservice.model.MenuPk;
import com.marco.menuservice.repositories.MenusRepo;
import com.marco.menuservice.services.implementations.BusinsessLogicImpl;
import com.marco.menuservice.services.interfaces.RestClientInt;

@ExtendWith(MockitoExtension.class)
public class BusinsessLogicImplTests {

    @InjectMocks
    private BusinsessLogicImpl service = new BusinsessLogicImpl();

    @Mock
    private MenusRepo repo;

    @Mock
    private RestClientInt restClient;

    @Mock
    private DishesServiceProperties prpDishServ;

    private String dishName = "DISH";
    private String dishName2 = "DISH2";
    private String menuName = "MENU";
    private Menu menu1 = new Menu();
    private Menu menu2 = new Menu();
    {
        menu1.setId(new MenuPk(menuName, dishName));
        menu2.setId(new MenuPk(menuName, dishName2));
    }

    @Test
    public void insertMenu() throws MarcoException {
        when(repo.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());

        assertTrue(service.insertMenu(menu1));
    }

    @Test
    public void deleteMenu() throws MarcoException {
        when(repo.findById(ArgumentMatchers.any())).thenReturn(Optional.of(menu1));

        assertTrue(service.deleteMenu(menu1));
    }

    @Test
    public void findDishesForMenu() throws MarcoException {
        when(repo.findByIdMenuName(menuName)).thenReturn(Arrays.asList(menu1, menu2));

        List<Menu> list = service.findDishesForMenu(menuName);
        assertFalse(list.isEmpty());
    }

    @Test
    public void deleteAllDishesForMenu() throws MarcoException {
        when(repo.findByIdMenuName(menuName)).thenReturn(Arrays.asList(menu1, menu2));

        assertTrue(service.deleteAllDishesForMenu(menuName));
    }

    @Test
    public void findAllMenus() {
        when(repo.findAll()).thenReturn(Arrays.asList(menu1, menu2));

        List<Menu> list = service.findAllMenus();
        assertFalse(list.isEmpty());
    }

    @Test
    public void checkIfDishExistInDishesService() throws MarcoException {
        when(prpDishServ.getProtocol()).thenReturn("http");
        when(prpDishServ.getHost()).thenReturn("host");
        when(prpDishServ.getFindDishByName(dishName)).thenReturn("dummy");
        when(restClient.performGetRequest(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(ClientResponse.create(HttpStatus.OK).build());

        assertTrue(service.checkIfDishExistInDishesService(dishName));
    }
}
