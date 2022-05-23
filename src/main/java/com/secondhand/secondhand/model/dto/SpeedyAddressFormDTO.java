package com.secondhand.secondhand.model.dto;

public class SpeedyAddressFormDTO {

    private String name;
    private Long id;

    public SpeedyAddressFormDTO() {
    }

    public String getName() {
        return name;
    }

    public SpeedyAddressFormDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Long getId() {
        return id;
    }

    public SpeedyAddressFormDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
