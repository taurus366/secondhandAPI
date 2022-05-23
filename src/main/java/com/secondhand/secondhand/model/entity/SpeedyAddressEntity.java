package com.secondhand.secondhand.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "speedy_addresses")
public class SpeedyAddressEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    public SpeedyAddressEntity() {
    }

    public String getName() {
        return name;
    }

    public SpeedyAddressEntity setName(String name) {
        this.name = name;
        return this;
    }

}
