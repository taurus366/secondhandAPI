package com.secondhand.secondhand.model.binding;

public class UserChangePersonalDataBindingModel {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String userEmail;

    public UserChangePersonalDataBindingModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserChangePersonalDataBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserChangePersonalDataBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserChangePersonalDataBindingModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public UserChangePersonalDataBindingModel setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }
}
