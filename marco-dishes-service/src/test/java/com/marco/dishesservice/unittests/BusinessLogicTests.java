package com.marco.dishesservice.unittests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.marco.dishesservice.errors.MarcoException;
import com.marco.dishesservice.model.Dish;
import com.marco.dishesservice.repositories.interfaces.DishRepository;
import com.marco.dishesservice.services.implementations.BusinsessLogicImpl;
import com.marco.dishesservice.services.interfaces.BusinsessLogicInt;
import com.marco.dishesservice.services.interfaces.ErrorServiceInt;

/**
 * This class provides the Unit tests for the Business Logic service <br>
 * TODO add some bad scenarios
 * 
 * @author msolina
 *
 */
@ExtendWith(MockitoExtension.class)
public class BusinessLogicTests {

    @InjectMocks
    private BusinsessLogicInt service = new BusinsessLogicImpl();

    @Mock
    private DishRepository repo;

    @Mock
    private ErrorServiceInt errService;

    /*
     * Some hard coded dishes
     */
    private int dishNumber = 0;
    private String name = "ONE";
    private List<Dish> dishes = Arrays.asList(new Dish("ONE", 1), new Dish("TWO", 2));

    @BeforeEach
    public void before() {
        lenient().when(repo.findByName(name)).thenReturn(Optional.of(dishes.get(dishNumber)));
        lenient().when(repo.findAll()).thenReturn(dishes);
    }

    @Test
    public void insertDishTest() throws MarcoException {
        when(repo.save(ArgumentMatchers.any())).thenReturn(dishes.get(dishNumber));
        assertTrue(service.insertDish(dishes.get(dishNumber + 1)));
    }

    @Test
    public void updateDishTest() throws MarcoException {
        assertTrue(service.updateDish(dishes.get(dishNumber)));
    }

    @Test
    public void deleteDishTest() throws MarcoException {
        assertTrue(service.deleteDish(name));
    }

    @Test
    public void findDishTest() throws MarcoException {
        Optional<Dish> o = service.findDish(name);
        assertTrue(o.isPresent());
    }

    @Test
    public void findAllDishTest() throws MarcoException {
        List<Dish> list = service.findAllDish();
        assertThat(list).isNotNull();
    }
}
