package com.secondhand.secondhand.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "addresses")
public class AddressEntity extends BaseEntity{

    @Column()
    private String firstName;

    @Column()
    private String lastName;

    @Column()
    private String phoneNumber;

    @Column()
    private String city;

    @Column()
    private String municipality;

    @Column()
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

//    @ManyToOne()
//    private UserEntity user;

//    @ManyToOne()
//    private UserEntity user;


    public AddressEntity() {
    }

    public String getFirstName() {
        return firstName;
    }

    public AddressEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AddressEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public AddressEntity setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddressEntity setCity(String city) {
        this.city = city;
        return this;
    }

    public String getMunicipality() {
        return municipality;
    }

    public AddressEntity setMunicipality(String municipality) {
        this.municipality = municipality;
        return this;
    }

    public String getZip() {
        return zip;
    }

    public AddressEntity setZip(String zip) {
        this.zip = zip;
        return this;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public AddressEntity setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public AddressEntity setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public AddressEntity setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public String getBlock() {
        return block;
    }

    public AddressEntity setBlock(String block) {
        this.block = block;
        return this;
    }

    public String getEntry() {
        return entry;
    }

    public AddressEntity setEntry(String entry) {
        this.entry = entry;
        return this;
    }

    public String getFloor() {
        return floor;
    }

    public AddressEntity setFloor(String floor) {
        this.floor = floor;
        return this;
    }

    public String getApartment() {
        return apartment;
    }

    public AddressEntity setApartment(String apartment) {
        this.apartment = apartment;
        return this;
    }

    public String getDetailsAboutAddress() {
        return detailsAboutAddress;
    }

    public AddressEntity setDetailsAboutAddress(String detailsAboutAddress) {
        this.detailsAboutAddress = detailsAboutAddress;
        return this;
    }

//    public UserEntity getUser() {
//        return user;
//    }
//
//    public AddressEntity setUser(UserEntity user) {
//        this.user = user;
//        return this;
//    }
}
