package com.secondhand.secondhand.model.service;

public class SpeedyNewAddressServiceModel {

    private String userEmail;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Long cityId;
    private Long speedyOfficeId;


    public SpeedyNewAddressServiceModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public SpeedyNewAddressServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SpeedyNewAddressServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public SpeedyNewAddressServiceModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Long getCityId() {
        return cityId;
    }

    public SpeedyNewAddressServiceModel setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public Long getSpeedyOfficeId() {
        return speedyOfficeId;
    }

    public SpeedyNewAddressServiceModel setSpeedyOfficeId(Long speedyOfficeId) {
        this.speedyOfficeId = speedyOfficeId;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public SpeedyNewAddressServiceModel setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }
}
