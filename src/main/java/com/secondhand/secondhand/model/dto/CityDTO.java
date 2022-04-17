package com.secondhand.secondhand.model.dto;

public class CityDTO {

    private Long id;

    private String city;

    private String lat;

    private String lng;

    private String country;

    private String countryCode;

    private String region;

    private String postCode;

    public CityDTO() {
    }

    public Long getId() {
        return id;
    }

    public CityDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCity() {
        return city;
    }

    public CityDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getLat() {
        return lat;
    }

    public CityDTO setLat(String lat) {
        this.lat = lat;
        return this;
    }

    public String getLng() {
        return lng;
    }

    public CityDTO setLng(String lng) {
        this.lng = lng;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public CityDTO setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public CityDTO setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public CityDTO setRegion(String region) {
        this.region = region;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public CityDTO setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }
}
