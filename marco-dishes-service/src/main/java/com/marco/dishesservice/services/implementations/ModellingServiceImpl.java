package com.marco.dishesservice.services.implementations;

import com.marco.dishesservice.dto.ApiDish;
import com.marco.dishesservice.model.Dish;
import com.marco.dishesservice.services.interfaces.ModellingServiceInt;

public class ModellingServiceImpl implements ModellingServiceInt {

    @Override
    public ApiDish fromDishToApiDish(Dish dish) {
        if (dish == null) {
            return null;
        }
        ApiDish ad = new ApiDish();
        ad.setName(dish.getName());
        ad.setCalories(dish.getCalories());
        return ad;
    }

    @Override
    public Dish fromApiDishToDish(ApiDish apiDish) {
        if (apiDish == null) {
            return null;
        }

        Dish d = new Dish();
        d.setName(apiDish.getName());
        d.setCalories(apiDish.getCalories());
        return d;
    }

}
