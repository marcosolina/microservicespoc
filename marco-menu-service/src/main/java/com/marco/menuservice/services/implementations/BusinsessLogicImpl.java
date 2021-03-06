package com.marco.menuservice.services.implementations;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.ClientResponse;

import com.marco.menuservice.config.properties.DishesServiceProperties;
import com.marco.menuservice.errors.MarcoException;
import com.marco.menuservice.model.sql.Menu;
import com.marco.menuservice.repositories.sql.MenusRepo;
import com.marco.menuservice.services.interfaces.BusinsessLogicInt;
import com.marco.menuservice.services.interfaces.ErrorServiceInt;
import com.marco.menuservice.services.interfaces.RestClientInt;
import com.marco.menuservice.utils.PricesConstants;

@Transactional
public class BusinsessLogicImpl implements BusinsessLogicInt {

    @Autowired
    private MenusRepo repo;

    @Autowired
    private ErrorServiceInt errServ;

    @Autowired
    private DishesServiceProperties prpDishServ;

    @Autowired
    private RestClientInt restClient;

    @Override
    public boolean checkIfDishExistInDishesService(String dishName) throws MarcoException {

        try {
            URL url = new URL(prpDishServ.getProtocol(), prpDishServ.getHost(), prpDishServ.getFindDishByName(dishName));
            ClientResponse resp = restClient.performGetRequest(PricesConstants.TOKEN_DISHES_REGISTRATION_ID, url, null, null);
            return resp != null && resp.statusCode() == HttpStatus.OK;
        } catch (MalformedURLException e) {
            throw errServ.buildSimpleExceptionWithStatus(HttpStatus.BAD_GATEWAY, "MENU0001", dishName);
        }

    }

    @Override
    public boolean insertMenu(Menu menu) throws MarcoException {
        if (repo.findById(menu.getId()).isPresent()) {
            throw errServ.buildSimpleExceptionWithStatus(HttpStatus.CONFLICT, "MENU0002", menu.getId().getDishName(), menu.getId().getMenuName());
        }
        repo.save(menu);
        return true;
    }

    @Override
    public boolean deleteMenu(Menu menu) throws MarcoException {
        repo.delete(repo.findById(menu.getId()).orElseThrow(() -> errServ.buildSimpleExceptionWithStatus(HttpStatus.NOT_FOUND, "MENU0003", menu.getId().getDishName(), menu.getId().getMenuName())));
        return true;
    }

    @Override
    public List<Menu> findDishesForMenu(String menuName) throws MarcoException {
        List<Menu> list = repo.findByIdMenuName(menuName);
        if(list == null || list.isEmpty()) {
            throw errServ.buildSimpleExceptionWithStatus(HttpStatus.NOT_FOUND, "MENU0004", menuName);
        }
        return list;
    }

    @Override
    public boolean deleteAllDishesForMenu(String menuName) throws MarcoException {
        List<Menu> list = repo.findByIdMenuName(menuName);
        if (list == null || list.isEmpty()) {
            throw errServ.buildSimpleExceptionWithStatus(HttpStatus.NOT_FOUND, "MENU0004", menuName);
        }
        repo.deleteByIdMenuName(menuName);
        return true;
    }

    @Override
    public List<Menu> findAllMenus() {
        List<Menu> list = new ArrayList<>();
        repo.findAll().forEach(list::add);
        return list;
    }

}
