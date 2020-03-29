package com.marco.dishesservice.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Pojo used in the Rest API to return a list of {@link ApiDish}
 * 
 * @author msolina
 *
 */
@ApiModel(value = "Dishes wrapper", description = "It contains the list of dishes")
public class ApiDishes implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "List of dishes")
    private List<ApiDish> dishes = new ArrayList<>();

    public boolean addApiDish(ApiDish apiDish) {
        return dishes.add(apiDish);
    }

    public List<ApiDish> getDishes() {
        return dishes;
    }

    public void setDishes(List<ApiDish> dishes) {
        this.dishes = dishes;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
