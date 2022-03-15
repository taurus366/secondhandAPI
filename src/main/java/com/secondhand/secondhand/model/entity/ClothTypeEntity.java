package com.secondhand.secondhand.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cloth_type")
public class ClothTypeEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String gender;



    public ClothTypeEntity() {

    }


    public String getName() {
        return name;
    }

    public ClothTypeEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public ClothTypeEntity setGender(String gender) {
        this.gender = gender;
        return this;
    }
}
