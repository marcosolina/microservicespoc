package com.marco.menuservice.dto;

import java.io.Serializable;

/**
 * Pojo used in the Rest API
 * 
 * @author msolina
 *
 */
public class ApiPrice implements Serializable {
    private static final long serialVersionUID = 1L;
    private String dishName;
    private Integer price;
    private boolean available;

    public ApiPrice() {

    }

    public ApiPrice(String dishName, Integer price) {
        this.dishName = dishName;
        this.price = price;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dishName == null) ? 0 : dishName.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
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
        ApiPrice other = (ApiPrice) obj;
        if (dishName == null) {
            if (other.dishName != null)
                return false;
        } else if (!dishName.equals(other.dishName))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        return true;
    }

}
