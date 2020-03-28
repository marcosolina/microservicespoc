package com.marco.dishesservice.unittests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.marco.dishesservice.dto.ApiDish;
import com.marco.dishesservice.model.Dish;
import com.marco.dishesservice.services.implementations.ModellingServiceImpl;
import com.marco.dishesservice.services.interfaces.ModellingServiceInt;

/**
 * This class provides some unit tests for the Model service TODO add some bad
 * scenarios
 * 
 * @author msolina
 *
 */
@ExtendWith(MockitoExtension.class)
public class ModellingServiceTests {

    private ModellingServiceInt service = new ModellingServiceImpl();

    private Dish dish = new Dish("ONE", 1);
    private ApiDish apiDish = new ApiDish("ONE", 1);

    @Test
    public void fromDishToApiDishTest() {
        assertThat(service.fromApiDishToDish(apiDish)).isEqualTo(dish);
    }

    @Test
    public void fromApiDishToDishTest() {
        assertThat(service.fromDishToApiDish(dish)).isEqualTo(apiDish);

    }

}
