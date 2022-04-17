package com.secondhand.secondhand.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class CityEntity extends BaseEntity {

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String lat;

    @Column(nullable = false)
    private String lng;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String countryCode;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String postCode;

    public CityEntity() {
    }

    public String getCity() {
        return city;
    }

    public CityEntity setCity(String city) {
        this.city = city;
        return this;
    }

    public String getLat() {
        return lat;
    }

    public CityEntity setLat(String lat) {
        this.lat = lat;
        return this;
    }

    public String getLng() {
        return lng;
    }

    public CityEntity setLng(String lng) {
        this.lng = lng;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public CityEntity setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public CityEntity setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public CityEntity setRegion(String region) {
        this.region = region;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public CityEntity setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }
}
