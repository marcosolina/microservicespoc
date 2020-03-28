package com.marco.dishesservice.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.marco.dishesservice.errors.MarcoException;
import com.marco.dishesservice.model.Dish;
import com.marco.dishesservice.repositories.interfaces.DishRepository;
import com.marco.dishesservice.services.interfaces.BusinsessLogicInt;
import com.marco.dishesservice.services.interfaces.ErrorServiceInt;

public class BusinsessLogicImpl implements BusinsessLogicInt {

    @Autowired
    private DishRepository repo;

    @Autowired
    private ErrorServiceInt errService;

    @Override
    public boolean insertDish(Dish dish) throws MarcoException {
        Optional<Dish> od = repo.findByName(dish.getName());
        if (od.isPresent()) {
            throw errService.buildSimpleException("DISH0001", dish.getName());
        }
        repo.save(dish);

        return true;
    }

    @Override
    public boolean updateDish(Dish dish) throws MarcoException {
        repo.findByName(dish.getName()).orElseThrow(() -> errService.buildSimpleException("DISH0002", dish.getName()));
        repo.save(dish);
        return true;
    }

    @Override
    public boolean deleteDish(String name) throws MarcoException {
        repo.delete(repo.findByName(name).orElseThrow(() -> errService.buildSimpleException("DISH0003", name)));
        return true;
    }

    @Override
    public Optional<Dish> findDish(String name) throws MarcoException {
        return repo.findByName(name);
    }

    @Override
    public List<Dish> findAllDish() {
        List<Dish> dishes = new ArrayList<>();
        repo.findAll().forEach(dish -> {
            dishes.add(dish);
        });
        return dishes;
    }

}
