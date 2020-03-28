package com.marco.ingridientsservice.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "INGREDIENTS")
public class Ingredient {

    @EmbeddedId
    private IngredientPk id;

    public IngredientPk getId() {
        return id;
    }

    public Ingredient setId(IngredientPk id) {
        this.id = id;
        return this;
    }

}
