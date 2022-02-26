package com.secondhand.secondhand.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cloth_composition")
public class ClothCompositionEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;

    public ClothCompositionEntity() {
    }

    public String getName() {
        return name;
    }

    public ClothCompositionEntity setName(String name) {
        this.name = name;
        return this;
    }
}
