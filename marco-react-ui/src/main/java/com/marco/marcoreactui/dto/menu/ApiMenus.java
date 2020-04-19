package com.marco.marcoreactui.dto.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class wraps the list of {@link ApiMenu} exchanged with the REST APIs
 * 
 * @author msolina
 *
 */
public class ApiMenus implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<ApiMenu> menus = new ArrayList<>();

    public List<ApiMenu> getMenus() {
        return menus;
    }

    public boolean addMenu(ApiMenu menu) {
        return menus.add(menu);
    }

    public void setMenus(List<ApiMenu> menus) {
        this.menus = menus;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
