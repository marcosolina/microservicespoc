package com.marco.pricesservice.servicies.implementations;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.ClientResponse;

import com.marco.pricesservice.config.properties.DishesServiceProperties;
import com.marco.pricesservice.errors.MarcoException;
import com.marco.pricesservice.model.Price;
import com.marco.pricesservice.repositories.PriceRepository;
import com.marco.pricesservice.servicies.interfaces.BusinsessLogicInt;
import com.marco.pricesservice.servicies.interfaces.ErrorServiceInt;
import com.marco.pricesservice.servicies.interfaces.RestClientInt;
import com.marco.pricesservice.utils.PricesConstants;

@Transactional
public class BusinsessLogicImpl implements BusinsessLogicInt {

    @Autowired
    private PriceRepository repo;

    @Autowired
    private ErrorServiceInt errServ;

    @Autowired
    private DishesServiceProperties prpDishServ;
    
    @Autowired
    private RestClientInt restClient;
    
    @Override
    public Price getPriceForDishName(String dishName) throws MarcoException {
        return repo.findByDishName(dishName).orElseThrow(()-> errServ.buildSimpleExceptionWithStatus(HttpStatus.NOT_FOUND, "PRI0001", dishName));
    }

    @Override
    public boolean insertPrice(Price price) throws MarcoException {
        if(!checkIfDishExistInDishesService(price.getDishName())) {
            throw errServ.buildSimpleExceptionWithStatus(HttpStatus.BAD_GATEWAY, "PRI0006", price.getDishName());
        }
        if (repo.findByDishName(price.getDishName()).isPresent()) {
            throw errServ.buildSimpleExceptionWithStatus(HttpStatus.CONFLICT, "PRI0002", price.getDishName());
        }
        repo.save(price);
        return true;
    }

    @Override
    public boolean updatePrice(Price price) throws MarcoException {
        Price p = repo.findByDishName(price.getDishName()).orElseThrow(()-> errServ.buildSimpleException("PRI0003"));
        p.setPrice(price.getPrice());
        repo.save(p);
        return true;
    }

    @Override
    public List<Price> getAllPrices() {
        List<Price> list = new ArrayList<>();
        repo.findAll().forEach(list::add);
        return list;
    }

    @Override
    public boolean deletePriceForDishName(String dishName) throws MarcoException {
        Price price = repo.findByDishName(dishName).orElseThrow(()-> errServ.buildSimpleExceptionWithStatus(HttpStatus.NOT_FOUND, "PRI0004", dishName));
        repo.delete(price);
        return true;
    }

    @Override
    public boolean checkIfDishExistInDishesService(String dishName) throws MarcoException {
        try {
            URL url = new URL(prpDishServ.getProtocol(), prpDishServ.getHost(), prpDishServ.getFindDishByName(dishName));
            ClientResponse resp = restClient.performGetRequest(PricesConstants.TOKEN_DISHES_REGISTRATION_ID, url, null, null);
            return resp != null && resp.statusCode() == HttpStatus.OK;
        } catch (MalformedURLException e) {
            throw errServ.buildSimpleExceptionWithStatus(HttpStatus.BAD_GATEWAY, "PRI0005", dishName);
        }
    }

}
