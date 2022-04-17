package com.secondhand.secondhand.model.dto;

import javax.persistence.Column;

public class addressDTO {


    private String firstName;


    private String lastName;


    private String phoneNumber;


    private String city;


    private String municipality;


    private String zip;

    private String neighborhood;

    private String street;

    private String streetNumber;

    private String block;

    private String entry;

    private String floor;

    private String apartment;

    private String detailsAboutAddress;

    private String userEmail;

    public String getFirstName() {
        return firstName;
    }

    public addressDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public addressDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public addressDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getCity() {
        return city;
    }

    public addressDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getMunicipality() {
        return municipality;
    }

    public addressDTO setMunicipality(String municipality) {
        this.municipality = municipality;
        return this;
    }

    public String getZip() {
        return zip;
    }

    public addressDTO setZip(String zip) {
        this.zip = zip;
        return this;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public addressDTO setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public addressDTO setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public addressDTO setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public String getBlock() {
        return block;
    }

    public addressDTO setBlock(String block) {
        this.block = block;
        return this;
    }

    public String getEntry() {
        return entry;
    }

    public addressDTO setEntry(String entry) {
        this.entry = entry;
        return this;
    }

    public String getFloor() {
        return floor;
    }

    public addressDTO setFloor(String floor) {
        this.floor = floor;
        return this;
    }

    public String getApartment() {
        return apartment;
    }

    public addressDTO setApartment(String apartment) {
        this.apartment = apartment;
        return this;
    }

    public String getDetailsAboutAddress() {
        return detailsAboutAddress;
    }

    public addressDTO setDetailsAboutAddress(String detailsAboutAddress) {
        this.detailsAboutAddress = detailsAboutAddress;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public addressDTO setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }
}
