package com.secondhand.secondhand.model.dto;

public class SpeedyAddressDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String city;
    private String address;

    public SpeedyAddressDTO() {
    }

    public Long getId() {
        return id;
    }

    public SpeedyAddressDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public SpeedyAddressDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public SpeedyAddressDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getCity() {
        return city;
    }

    public SpeedyAddressDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public SpeedyAddressDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SpeedyAddressDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
