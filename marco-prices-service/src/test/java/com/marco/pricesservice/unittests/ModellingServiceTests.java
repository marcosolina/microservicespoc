package com.marco.pricesservice.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.marco.pricesservice.dto.ApiPrice;
import com.marco.pricesservice.model.Price;
import com.marco.pricesservice.servicies.implementations.ModellingServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ModellingServiceTests {

    private ModellingServiceImpl service = new ModellingServiceImpl();

    private String dishName = "DISHNAME";
    private Price price = new Price(dishName, 10);
    private ApiPrice apiPrice = new ApiPrice(dishName, 10);

    @Test
    public void fromPriceToApiPrice() {
        ApiPrice ap = service.fromPriceToApiPrice(price);
        assertEquals(price.getDishName(), ap.getDishName());
        assertEquals(price.getPrice(), ap.getPrice());
    }

    @Test
    public void fromApiPriceToPrice() {
        Price p = service.fromApiPriceToPrice(apiPrice);
        assertEquals(price, p);
    }

}
