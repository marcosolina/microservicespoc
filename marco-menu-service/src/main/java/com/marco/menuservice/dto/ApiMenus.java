package com.marco.menuservice.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ApiMenus implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<ApiMenu> menus;

    public List<ApiMenu> getMenus() {
        return menus;
    }

    public boolean addMenu(ApiMenu menu) {
        if (menus == null) {
            menus = new ArrayList<>();
        }
        return menus.add(menu);
    }

    public void setMenus(List<ApiMenu> menus) {
        this.menus = menus;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
