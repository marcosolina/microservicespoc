package com.marco.menuservice.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MENUS")
public class Menu {

    @EmbeddedId
    private MenuPk id;

    public MenuPk getId() {
        return id;
    }

    public Menu setId(MenuPk id) {
        this.id = id;
        return this;
    }

}
