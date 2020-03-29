package com.marco.menuservice.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This class wraps the list of {@link ApiMenu} exchanged with the REST APIs
 * 
 * @author msolina
 *
 */
@ApiModel(value = "Menus wrapper", description = "It contains the list of Menus")
public class ApiMenus implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "List of menus")
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
