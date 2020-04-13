package com.marco.marcoreactui.dto.dishes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Pojo used in the Rest API to return a list of {@link ApiDish}
 * 
 * @author msolina
 *
 */
public class ApiDishes implements Serializable {
    private static final long serialVersionUID = 1L;
    
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
