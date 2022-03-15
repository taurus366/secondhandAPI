package com.secondhand.secondhand.model.dto;

public class ClothTypeDTO {

    private String name;
    private String gender;

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
}
