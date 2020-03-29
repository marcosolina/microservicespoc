package com.marco.menuservice.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This class represents the Menu definition used in the rest API
 * 
 * @author msolina
 *
 */
@ApiModel(value = "Menu definition", description = "The menu definition contains the list of dishes")
public class ApiMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Menu name")
    private String menuName;

    @ApiModelProperty(value = "List of dishes that composes this menu")
    private Set<String> dishes;

    public ApiMenu() {

    }

    public ApiMenu(String menuName, Set<String> dishes) {
        this.menuName = menuName;
        this.dishes = dishes;
    }

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
