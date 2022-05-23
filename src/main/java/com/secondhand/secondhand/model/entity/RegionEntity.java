package com.secondhand.secondhand.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "region")
public class RegionEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;

    public RegionEntity() {
    }

    public String getName() {
        return name;
    }

    public RegionEntity setName(String name) {
        this.name = name;
        return this;
    }
}
