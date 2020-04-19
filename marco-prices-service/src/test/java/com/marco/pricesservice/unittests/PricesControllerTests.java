package com.marco.pricesservice.unittests;

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

import com.marco.pricesservice.controllers.PricesController;
import com.marco.pricesservice.dto.ApiPrice;
import com.marco.pricesservice.dto.ApiPrices;
import com.marco.pricesservice.errors.MarcoException;
import com.marco.pricesservice.model.sql.Price;
import com.marco.pricesservice.servicies.interfaces.BusinsessLogicInt;
import com.marco.pricesservice.servicies.interfaces.ModellingServiceInt;

@ExtendWith(MockitoExtension.class)
public class PricesControllerTests {

    @InjectMocks
    private PricesController controller = new PricesController();

    @Mock
    private BusinsessLogicInt bli;

    @Mock
    private ModellingServiceInt msi;

    private String dishName = "DISHNAME";
    private String dishName2 = "DISHNAME2";
    private Price price = new Price(dishName, 10);
    private Price price2 = new Price(dishName2, 20);
    private ApiPrice apiPrice = new ApiPrice(dishName, 10);
    private ApiPrice apiPrice2 = new ApiPrice(dishName2, 20);

    @Test
    public void getAllPrices() throws MarcoException {
        when(bli.getAllPrices()).thenReturn(Arrays.asList(price, price2));
        when(msi.fromPriceToApiPrice(price)).thenReturn(apiPrice);
        when(msi.fromPriceToApiPrice(price2)).thenReturn(apiPrice2);
        when(bli.checkIfDishExistInDishesService(ArgumentMatchers.anyString())).thenReturn(true);

        ResponseEntity<ApiPrices> resp = controller.getAllPrices();
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertFalse(resp.getBody().getPrices().isEmpty());
    }

    @Test
    public void findByName() throws MarcoException {
        when(bli.getPriceForDishName(dishName)).thenReturn(price);
        when(msi.fromPriceToApiPrice(price)).thenReturn(apiPrice);

        ResponseEntity<ApiPrice> resp = controller.findByName(dishName);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertEquals(apiPrice, resp.getBody());
    }

    @Test
    public void insertPrice() throws MarcoException {
        when(msi.fromApiPriceToPrice(apiPrice)).thenReturn(price);
        when(bli.insertPrice(price)).thenReturn(true);

        ResponseEntity<Void> resp = controller.insertPrice(apiPrice);
        assertEquals(HttpStatus.CREATED, resp.getStatusCode());
    }

    @Test
    public void updatePrice() throws MarcoException {
        when(msi.fromApiPriceToPrice(apiPrice)).thenReturn(price);
        when(bli.updatePrice(price)).thenReturn(true);

        ResponseEntity<Void> resp = controller.updatePrice(apiPrice);
        assertEquals(HttpStatus.NO_CONTENT, resp.getStatusCode());
    }

    @Test
    public void deletePrice() throws MarcoException {
        when(bli.deletePriceForDishName(dishName)).thenReturn(true);

        ResponseEntity<Void> resp = controller.deletePrice(dishName);
        assertEquals(HttpStatus.NO_CONTENT, resp.getStatusCode());
    }

}