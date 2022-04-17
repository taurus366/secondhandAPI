package com.secondhand.secondhand.model.binding;

import javax.persistence.Column;

public class UserAddressBindingModel {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String city;


    private String municipality;

    @Column(nullable = false)
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

    public UserAddressBindingModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserAddressBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserAddressBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserAddressBindingModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UserAddressBindingModel setCity(String city) {
        this.city = city;
        return this;
    }

    public String getMunicipality() {
        return municipality;
    }

    public UserAddressBindingModel setMunicipality(String municipality) {
        this.municipality = municipality;
        return this;
    }

    public String getZip() {
        return zip;
    }

    public UserAddressBindingModel setZip(String zip) {
        this.zip = zip;
        return this;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public UserAddressBindingModel setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public UserAddressBindingModel setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public UserAddressBindingModel setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public String getBlock() {
        return block;
    }

    public UserAddressBindingModel setBlock(String block) {
        this.block = block;
        return this;
    }

    public String getEntry() {
        return entry;
    }

    public UserAddressBindingModel setEntry(String entry) {
        this.entry = entry;
        return this;
    }

    public String getFloor() {
        return floor;
    }

    public UserAddressBindingModel setFloor(String floor) {
        this.floor = floor;
        return this;
    }

    public String getApartment() {
        return apartment;
    }

    public UserAddressBindingModel setApartment(String apartment) {
        this.apartment = apartment;
        return this;
    }

    public String getDetailsAboutAddress() {
        return detailsAboutAddress;
    }

    public UserAddressBindingModel setDetailsAboutAddress(String detailsAboutAddress) {
        this.detailsAboutAddress = detailsAboutAddress;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public UserAddressBindingModel setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }
}
