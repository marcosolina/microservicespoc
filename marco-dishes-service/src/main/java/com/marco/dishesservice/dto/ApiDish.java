package com.marco.dishesservice.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Pojo used in the Rest API
 * 
 * @author msolina
 *
 */
@ApiModel(value = "ApiDish", description = "This object represents the dish definition")
public class ApiDish implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "Name of the dish")
    private String name;
    @ApiModelProperty(value = "Calories of the dish")
    private Integer calories;

    public ApiDish() {

    }

    public ApiDish(String name, Integer calories) {
        this.name = name;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ApiDish other = (ApiDish) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}
