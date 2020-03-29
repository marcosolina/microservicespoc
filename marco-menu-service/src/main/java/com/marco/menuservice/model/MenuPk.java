package com.marco.menuservice.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * Standard JPA key
 * 
 * @author msolina
 *
 */
@Embeddable
public class MenuPk implements Serializable {
    private static final long serialVersionUID = 1L;
    private String menuName;
    private String dishName;

    public MenuPk() {
    }

    public MenuPk(String menuName, String dishName) {
        super();
        this.menuName = menuName;
        this.dishName = dishName;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dishName == null) ? 0 : dishName.hashCode());
        result = prime * result + ((menuName == null) ? 0 : menuName.hashCode());
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
        MenuPk other = (MenuPk) obj;
        if (dishName == null) {
            if (other.dishName != null)
                return false;
        } else if (!dishName.equals(other.dishName))
            return false;
        if (menuName == null) {
            if (other.menuName != null)
                return false;
        } else if (!menuName.equals(other.menuName))
            return false;
        return true;
    }

}
