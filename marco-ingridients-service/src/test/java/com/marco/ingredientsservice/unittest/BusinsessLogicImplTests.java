package com.marco.ingredientsservice.unittest;

import static org.assertj.core.api.Assertions.assertThat;
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

import com.marco.ingridientsservice.config.properties.DishesServiceProperties;
import com.marco.ingridientsservice.errors.MarcoException;
import com.marco.ingridientsservice.model.Ingredient;
import com.marco.ingridientsservice.model.IngredientPk;
import com.marco.ingridientsservice.repositories.IngredientsRepo;
import com.marco.ingridientsservice.services.implementations.BusinsessLogicImpl;
import com.marco.ingridientsservice.services.interfaces.RestClientInt;

@ExtendWith(MockitoExtension.class)
public class BusinsessLogicImplTests {

    @InjectMocks
    private BusinsessLogicImpl service = new BusinsessLogicImpl();

    @Mock
    private IngredientsRepo repo;

    @Mock
    private RestClientInt restClient;

    @Mock
    private DishesServiceProperties prpDishServ;

    private String dishName = "ONE";
    private Ingredient iOne = new Ingredient();
    private Ingredient iTwo = new Ingredient();

    {
        iOne.setId(new IngredientPk("ONE", "IONE"));
        iTwo.setId(new IngredientPk("TWO", "ITWO"));
    }

    private List<Ingredient> list = Arrays.asList(iOne, iTwo);

    @Test
    public void insertIngredient() throws MarcoException {
        when(repo.save(iOne)).thenReturn(iOne);
        when(repo.findById(iOne.getId())).thenReturn(Optional.empty());

        assertTrue(service.insertIngredient(iOne));
    }

    @Test
    public void deleteIngredient() throws MarcoException {
        when(repo.findById(iOne.getId())).thenReturn(Optional.of(iOne));
        assertTrue(service.deleteIngredient(iOne));
    }

    @Test
    public void findIngredients() throws MarcoException {
        when(repo.findByIdDishName(dishName)).thenReturn(Arrays.asList(iOne));

        List<Ingredient> list = service.findIngredients(dishName);
        assertThat(list).isNotNull();
        assertFalse(list.isEmpty());
    }

    @Test
    public void deleteIngredients() throws MarcoException {
        when(repo.findByIdDishName(dishName)).thenReturn(Arrays.asList(iOne));

        assertTrue(service.deleteIngredients(dishName));
    }

    @Test
    public void findAllIngredients() {
        when(repo.findAll()).thenReturn(list);
        List<Ingredient> list = service.findAllIngredients();

        assertThat(list).isNotNull();
        assertFalse(list.isEmpty());
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
