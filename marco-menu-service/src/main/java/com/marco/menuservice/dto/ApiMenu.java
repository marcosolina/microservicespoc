package com.marco.menuservice.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ApiMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    private String menuName;
    private Set<String> dishes;

    public boolean addDishName(String dishName) {
        if (this.dishes == null) {
            this.dishes = new HashSet<>();
        }

        return dishes.add(dishName);
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Set<String> getDishes() {
        return dishes;
    }

    public void setDishes(Set<String> dishes) {
        this.dishes = dishes;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
