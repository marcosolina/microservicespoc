package com.marco.pricesservice.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.marco.pricesservice.config.properties.DishesServiceProperties;
import com.marco.pricesservice.errors.MarcoException;
import com.marco.pricesservice.model.Price;
import com.marco.pricesservice.repositories.PriceRepository;
import com.marco.pricesservice.servicies.implementations.BusinsessLogicImpl;
import com.marco.pricesservice.servicies.interfaces.RestClientInt;

@ExtendWith(MockitoExtension.class)
public class BusinsessLogicImplTests {

    @InjectMocks
    private BusinsessLogicImpl service = new BusinsessLogicImpl();

    @Mock
    private RestClientInt restClient;

    @Mock
    private DishesServiceProperties prpDishServ;

    @Mock
    private PriceRepository repo;

    private String dishName = "DISHNAME";
    private String dishName2 = "DISHNAME2";
    private Price price = new Price(dishName, 10);
    private Price price2 = new Price(dishName2, 20);

    @Test
    public void getPriceForDishName() throws MarcoException {
        when(repo.findByDishName(dishName)).thenReturn(Optional.of(price));

        Price p = service.getPriceForDishName(dishName);
        assertEquals(price, p);
    }

    @Test
    public void insertPrice() throws MarcoException {
        when(prpDishServ.getProtocol()).thenReturn("http");
        when(prpDishServ.getHost()).thenReturn("host");
        when(prpDishServ.getFindDishByName(dishName)).thenReturn("dummy");
        when(restClient.performGetRequest(ArgumentMatchers.anyString(), ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(ClientResponse.create(HttpStatus.OK).build());

        when(repo.findByDishName(dishName)).thenReturn(Optional.empty());

        assertTrue(service.insertPrice(price));
    }

    @Test
    public void updatePrice() throws MarcoException {
        when(repo.findByDishName(dishName)).thenReturn(Optional.of(price));
        when(repo.save(price)).thenReturn(price);

        assertTrue(service.updatePrice(price));
    }

    @Test
    public void getAllPrices() {
        when(repo.findAll()).thenReturn(Arrays.asList(price, price2));
        
        List<Price> l = service.getAllPrices();
        assertFalse(l.isEmpty());
    }

    @Test
    public void deletePriceForDishName() throws MarcoException {
        when(repo.findByDishName(dishName)).thenReturn(Optional.of(price));
        
        assertTrue(service.deletePriceForDishName(dishName));
    }

    @Test
    public void checkIfDishExistInDishesService() throws MarcoException {
        when(prpDishServ.getProtocol()).thenReturn("http");
        when(prpDishServ.getHost()).thenReturn("host");
        when(prpDishServ.getFindDishByName(dishName)).thenReturn("dummy");
        when(restClient.performGetRequest(ArgumentMatchers.anyString(), ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(ClientResponse.create(HttpStatus.OK).build());

        assertTrue(service.checkIfDishExistInDishesService(dishName));
    }
}
