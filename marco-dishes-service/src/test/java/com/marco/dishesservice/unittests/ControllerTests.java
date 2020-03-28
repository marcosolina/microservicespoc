package com.marco.dishesservice.unittests;

import static org.assertj.core.api.Assertions.assertThat;
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
import org.springframework.http.ResponseEntity;

import com.marco.dishesservice.controllers.DishesController;
import com.marco.dishesservice.dto.ApiDish;
import com.marco.dishesservice.dto.ApiDishes;
import com.marco.dishesservice.errors.MarcoException;
import com.marco.dishesservice.model.Dish;
import com.marco.dishesservice.services.interfaces.BusinsessLogicInt;
import com.marco.dishesservice.services.interfaces.ModellingServiceInt;


/**
 * This class provides the Unit tests for the controller TODO add some bad
 * scenarios
 * 
 * @author msolina
 *
 */
@ExtendWith(MockitoExtension.class)
public class ControllerTests {
    @InjectMocks
    private DishesController controller = new DishesController();
    @Mock
    private BusinsessLogicInt bli;
    @Mock
    private ModellingServiceInt msi;

    private int dishNumber = 0;

    private List<Dish> dishes = Arrays.asList(new Dish("ONE", 1), new Dish("TWO", 2));
    private List<ApiDish> apiDishes = Arrays.asList(new ApiDish("ONE", 1), new ApiDish("TWO", 2));

    @Test
    public void testGetAll() throws MarcoException {
        when(bli.findAllDish()).thenReturn(dishes);
        when(msi.fromDishToApiDish(ArgumentMatchers.any())).thenReturn(apiDishes.get(dishNumber++));

        ResponseEntity<ApiDishes> response = controller.getAllDishes();

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getDishes()).isNotNull();
        assertThat(response.getBody().getDishes().size()).isEqualTo(dishes.size());
    }

    @Test
    public void getSingleDish() throws MarcoException {
        String name = "ONE";
        when(bli.findDish(name)).thenReturn(Optional.of(dishes.get(dishNumber)));
        when(msi.fromDishToApiDish(dishes.get(dishNumber))).thenReturn(apiDishes.get(dishNumber));

        ResponseEntity<ApiDish> response = controller.findByName(name);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void insertOne() throws MarcoException {
        when(bli.insertDish(dishes.get(dishNumber))).thenReturn(true);
        when(msi.fromApiDishToDish(apiDishes.get(dishNumber))).thenReturn(dishes.get(dishNumber));

        ResponseEntity<Void> response = controller.insertDish(apiDishes.get(dishNumber));

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void updateOne() throws MarcoException {
        when(bli.updateDish(dishes.get(dishNumber))).thenReturn(true);
        when(msi.fromApiDishToDish(apiDishes.get(dishNumber))).thenReturn(dishes.get(dishNumber));

        ResponseEntity<Void> response = controller.updateDish(apiDishes.get(dishNumber));

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void deleteOne() throws MarcoException {
        String name = "ONE";
        when(bli.deleteDish(name)).thenReturn(true);

        ResponseEntity<Void> response = controller.deleteDish(name);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
