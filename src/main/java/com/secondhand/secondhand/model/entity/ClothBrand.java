package com.secondhand.secondhand.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cloth_brand")
public class ClothBrand extends BaseEntity{

    @Column(nullable = false)
    private String name;

    public ClothBrand() {
    }

    public String getName() {
        return name;
    }

    public ClothBrand setName(String name) {
        this.name = name;
        return this;
    }
}
