package com.marco.ingridientsservice.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class IngredientPk implements Serializable {
    private static final long serialVersionUID = 1L;
    private String dishName;
    private String ingredientName;

    public IngredientPk() {
    }

    public IngredientPk(String dishName, String ingredientName) {
        super();
        this.dishName = dishName;
        this.ingredientName = ingredientName;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dishName == null) ? 0 : dishName.hashCode());
        result = prime * result + ((ingredientName == null) ? 0 : ingredientName.hashCode());
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
        IngredientPk other = (IngredientPk) obj;
        if (dishName == null) {
            if (other.dishName != null)
                return false;
        } else if (!dishName.equals(other.dishName))
            return false;
        if (ingredientName == null) {
            if (other.ingredientName != null)
                return false;
        } else if (!ingredientName.equals(other.ingredientName))
            return false;
        return true;
    }

}
