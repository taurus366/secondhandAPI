package com.secondhand.secondhand.model.dto;

public class AddressDTO {

    private Long id;

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

    public Long getId() {
        return id;
    }

    public AddressDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public AddressDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AddressDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public AddressDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddressDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getMunicipality() {
        return municipality;
    }

    public AddressDTO setMunicipality(String municipality) {
        this.municipality = municipality;
        return this;
    }

    public String getZip() {
        return zip;
    }

    public AddressDTO setZip(String zip) {
        this.zip = zip;
        return this;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public AddressDTO setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public AddressDTO setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public AddressDTO setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public String getBlock() {
        return block;
    }

    public AddressDTO setBlock(String block) {
        this.block = block;
        return this;
    }

    public String getEntry() {
        return entry;
    }

    public AddressDTO setEntry(String entry) {
        this.entry = entry;
        return this;
    }

    public String getFloor() {
        return floor;
    }

    public AddressDTO setFloor(String floor) {
        this.floor = floor;
        return this;
    }

    public String getApartment() {
        return apartment;
    }

    public AddressDTO setApartment(String apartment) {
        this.apartment = apartment;
        return this;
    }

    public String getDetailsAboutAddress() {
        return detailsAboutAddress;
    }

    public AddressDTO setDetailsAboutAddress(String detailsAboutAddress) {
        this.detailsAboutAddress = detailsAboutAddress;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public AddressDTO setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }
}
