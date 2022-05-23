package com.secondhand.secondhand.model.service;

public class JsonCountryServiceModel {

    private String name;
    private String code;

    public JsonCountryServiceModel() {
    }

    public String getName() {
        return name;
    }

    public JsonCountryServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public JsonCountryServiceModel setCode(String code) {
        this.code = code;
        return this;
    }
}
