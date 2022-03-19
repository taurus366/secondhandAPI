package com.secondhand.secondhand.model.dto;

import com.secondhand.secondhand.model.entity.enums.ItemTypeEnum;

public class ClothTypeDTO {

    private String name;
    private String gender;
    private ItemTypeEnum type;

    public ClothTypeDTO() {
    }

    public String getName() {
        return name;
    }

    public ClothTypeDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public ClothTypeDTO setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public ItemTypeEnum getType() {
        return type;
    }

    public ClothTypeDTO setType(ItemTypeEnum type) {
        this.type = type;
        return this;
    }
}
