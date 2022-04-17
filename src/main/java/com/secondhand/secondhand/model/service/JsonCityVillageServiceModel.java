package com.secondhand.secondhand.model.service;

public class JsonCityVillageServiceModel {

    private String city;
    private String lat;
    private String lng;
    private String country;
    private String countryCode;
//    private String municipality; // obshtina
    private String region; // oblast
    private String postCode;

    public JsonCityVillageServiceModel() {
    }

    public String getCity() {
        return city;
    }

    public JsonCityVillageServiceModel setCity(String city) {
        this.city = city;
        return this;
    }

    public String getLat() {
        return lat;
    }

    public JsonCityVillageServiceModel setLat(String lat) {
        this.lat = lat;
        return this;
    }

    public String getLng() {
        return lng;
    }

    public JsonCityVillageServiceModel setLng(String lng) {
        this.lng = lng;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public JsonCityVillageServiceModel setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public JsonCityVillageServiceModel setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public JsonCityVillageServiceModel setRegion(String region) {
        this.region = region;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public JsonCityVillageServiceModel setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }
}
