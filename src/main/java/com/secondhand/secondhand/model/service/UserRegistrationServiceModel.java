package com.secondhand.secondhand.model.service;

import com.secondhand.secondhand.model.entity.enums.UserSexEnum;

public class UserRegistrationServiceModel {

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private UserSexEnum sex;

    public String getFirstName() {
        return firstName;
    }

    public UserRegistrationServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegistrationServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserSexEnum getSex() {
        return sex;
    }

    public UserRegistrationServiceModel setSex(UserSexEnum sex) {
        this.sex = sex;
        return this;
    }
}
