package com.secondhand.secondhand.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cloth_brand")
public class ClothBrandEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;

    public ClothBrandEntity() {
    }

    public String getName() {
        return name;
    }

    public ClothBrandEntity setName(String name) {
        this.name = name;
        return this;
    }
}
