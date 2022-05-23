package com.secondhand.secondhand.model.binding;

import javax.persistence.Column;

public class UserEditAddressBindingModel {

    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String city;

    @Column()
    private String municipality;

    @Column(nullable = false)
    private String zip;

    @Column()
    private String neighborhood;

    @Column()
    private String street;

    @Column()
    private String streetNumber;

    @Column()
    private String block;

    @Column()
    private String entry;

    @Column()
    private String floor;

    @Column()
    private String apartment;

    @Column()
    private String detailsAboutAddress;

    @Column()
    private String userEmail;

    public UserEditAddressBindingModel() {
    }

    public Long getId() {
        return id;
    }

    public UserEditAddressBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEditAddressBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEditAddressBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserEditAddressBindingModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UserEditAddressBindingModel setCity(String city) {
        this.city = city;
        return this;
    }

    public String getMunicipality() {
        return municipality;
    }

    public UserEditAddressBindingModel setMunicipality(String municipality) {
        this.municipality = municipality;
        return this;
    }

    public String getZip() {
        return zip;
    }

    public UserEditAddressBindingModel setZip(String zip) {
        this.zip = zip;
        return this;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public UserEditAddressBindingModel setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public UserEditAddressBindingModel setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public UserEditAddressBindingModel setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public String getBlock() {
        return block;
    }

    public UserEditAddressBindingModel setBlock(String block) {
        this.block = block;
        return this;
    }

    public String getEntry() {
        return entry;
    }

    public UserEditAddressBindingModel setEntry(String entry) {
        this.entry = entry;
        return this;
    }

    public String getFloor() {
        return floor;
    }

    public UserEditAddressBindingModel setFloor(String floor) {
        this.floor = floor;
        return this;
    }

    public String getApartment() {
        return apartment;
    }

    public UserEditAddressBindingModel setApartment(String apartment) {
        this.apartment = apartment;
        return this;
    }

    public String getDetailsAboutAddress() {
        return detailsAboutAddress;
    }

    public UserEditAddressBindingModel setDetailsAboutAddress(String detailsAboutAddress) {
        this.detailsAboutAddress = detailsAboutAddress;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public UserEditAddressBindingModel setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }
}
