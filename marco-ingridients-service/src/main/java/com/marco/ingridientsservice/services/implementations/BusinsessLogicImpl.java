package com.marco.ingridientsservice.services.implementations;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.ClientResponse;

import com.marco.ingridientsservice.config.properties.DishesServiceProperties;
import com.marco.ingridientsservice.errors.MarcoException;
import com.marco.ingridientsservice.model.Ingredient;
import com.marco.ingridientsservice.repositories.IngredientsRepo;
import com.marco.ingridientsservice.services.interfaces.BusinsessLogicInt;
import com.marco.ingridientsservice.services.interfaces.ErrorServiceInt;
import com.marco.ingridientsservice.services.interfaces.RestClientInt;
import com.marco.ingridientsservice.utils.PricesConstants;

@Transactional
public class BusinsessLogicImpl implements BusinsessLogicInt {

    @Autowired
    private IngredientsRepo repo;

    @Autowired
    private ErrorServiceInt errServ;
    
    @Autowired
    private DishesServiceProperties prpDishServ;
    
    @Autowired
    private RestClientInt restClient;

    @Override
    public boolean insertIngredient(Ingredient ingredient) throws MarcoException {
        if (repo.findById(ingredient.getId()).isPresent()) {
            throw errServ.buildSimpleException("ING0001", ingredient.getId().getIngredientName(), ingredient.getId().getDishName());
        }
        repo.save(ingredient);
        return true;
    }

    @Override
    public boolean deleteIngredient(Ingredient ingridient) throws MarcoException {
        repo.delete(repo.findById(ingridient.getId()).orElseThrow(()-> errServ.buildSimpleException("ING0003", ingridient.getId().getDishName(), ingridient.getId().getDishName())));
        return true;
    }

    @Override
    public List<Ingredient> findIngredients(String dishName) throws MarcoException {
    	List<Ingredient> list = repo.findByIdDishName(dishName);  
    	if(list == null || list.isEmpty()) {
    		throw errServ.buildSimpleExceptionWithStatus(HttpStatus.NOT_FOUND, "ING0004", dishName);
    	}
    	
        return list;
    }

    @Override
    public List<Ingredient> findAllIngredients() {
        List<Ingredient> list = new ArrayList<>();
        repo.findAll().forEach(list::add);
        return list;
    }

    @Override
    public boolean checkIfDishExistInDishesService(String dishName)throws MarcoException  {
        
        try {
            URL url = new URL(prpDishServ.getProtocol(), prpDishServ.getHost(), prpDishServ.getFindDishByName(dishName));
            ClientResponse resp = restClient.performGetRequest(PricesConstants.TOKEN_DISHES_REGISTRATION_ID, url, null, null);
            return resp != null && resp.statusCode() == HttpStatus.OK;
        } catch (MalformedURLException e) {
            throw errServ.buildSimpleExceptionWithStatus(HttpStatus.BAD_GATEWAY, "ING0002", dishName);
        }
        
    }

    @Override
    public boolean deleteIngredients(String dishName) throws MarcoException {
        List<Ingredient> list = findIngredients(dishName);
        if(list == null || list.isEmpty()) {
            throw errServ.buildSimpleExceptionWithStatus(HttpStatus.NOT_FOUND, "ING0004", dishName);
        }
        repo.deleteByIdDishName(dishName);
        return true;
    }

}
