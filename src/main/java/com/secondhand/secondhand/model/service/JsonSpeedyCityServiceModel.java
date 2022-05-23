package com.secondhand.secondhand.model.service;

import java.util.ArrayList;
import java.util.List;

public class JsonSpeedyCityServiceModel {

    private String city;
    private int postCode;
    private String region;
    private List<JsonSpeedyAddressListServiceModel> address = new ArrayList<>();
    private String country;
    private String countryCode;

    public String getCity() {
        return city;
    }

    public JsonSpeedyCityServiceModel setCity(String city) {
        this.city = city;
        return this;
    }

    public int getPostCode() {
        return postCode;
    }

    public JsonSpeedyCityServiceModel setPostCode(int postCode) {
        this.postCode = postCode;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public JsonSpeedyCityServiceModel setRegion(String region) {
        this.region = region;
        return this;
    }

    public List<JsonSpeedyAddressListServiceModel> getAddress() {
        return address;
    }

    public JsonSpeedyCityServiceModel setAddress(List<JsonSpeedyAddressListServiceModel> address) {
        this.address = address;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public JsonSpeedyCityServiceModel setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public JsonSpeedyCityServiceModel setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }
}
