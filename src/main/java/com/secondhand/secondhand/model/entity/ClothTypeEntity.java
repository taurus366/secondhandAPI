package com.secondhand.secondhand.model.entity;

import com.secondhand.secondhand.model.entity.enums.ItemTypeEnum;

import javax.persistence.*;

@Entity
@Table(name = "cloth_type")
public class ClothTypeEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemTypeEnum type;

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

    public ItemTypeEnum getType() {
        return type;
    }

    public ClothTypeEntity setType(ItemTypeEnum type) {
        this.type = type;
        return this;
    }
}
